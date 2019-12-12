
package edu.ucla.library.iiif.fester.handlers;

import info.freelibrary.util.Logger;
import info.freelibrary.util.LoggerFactory;

import edu.ucla.library.iiif.fester.Constants;
import edu.ucla.library.iiif.fester.HTTP;
import edu.ucla.library.iiif.fester.MessageCodes;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * A IIIF manifest deleter.
 */
public class DeleteManifestHandler extends AbstractManifestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteManifestHandler.class, Constants.MESSAGES);

    /**
     * Creates a handler that deletes IIIF manifests from Fester.
     *
     * @param aVertx A Vert.x instance
     * @param aConfig A JSON configuration
     */
    public DeleteManifestHandler(final Vertx aVertx, final JsonObject aConfig) {
        super(aVertx, aConfig);
    }

    @Override
    public void handle(final RoutingContext aContext) {
        final HttpServerResponse response = aContext.response();
        final HttpServerRequest request = aContext.request();
        final String idParam = request.getParam(Constants.MANIFEST_ID);
        final String manifestId;

        // If our manifest ID doesn't end with '.json' add it for third party tool convenience
        manifestId = !idParam.endsWith(Constants.JSON_EXT) ? idParam + Constants.JSON_EXT : idParam;

        myS3Client.delete(myS3Bucket, manifestId, deleteResponse -> {
            final int statusCode = deleteResponse.statusCode();

            switch (statusCode) {
                case HTTP.SUCCESS_NO_CONTENT:
                    response.setStatusCode(HTTP.SUCCESS_NO_CONTENT);
                    response.end();

                    break;
                case HTTP.FORBIDDEN:
                    response.setStatusCode(HTTP.FORBIDDEN);
                    response.end();

                    break;
                case HTTP.INTERNAL_SERVER_ERROR:
                    LOGGER.error(MessageCodes.MFS_014, manifestId);

                    response.setStatusCode(HTTP.INTERNAL_SERVER_ERROR);
                    response.end();

                    break;
                default:
                    LOGGER.warn(MessageCodes.MFS_013, statusCode, manifestId);

                    response.setStatusCode(statusCode);
                    response.end();
            }
        });
    }

}