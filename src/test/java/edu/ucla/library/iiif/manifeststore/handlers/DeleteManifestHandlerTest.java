
package edu.ucla.library.iiif.manifeststore.handlers;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;

import info.freelibrary.util.Logger;
import info.freelibrary.util.LoggerFactory;
import info.freelibrary.util.StringUtils;

import edu.ucla.library.iiif.manifeststore.Config;
import edu.ucla.library.iiif.manifeststore.Constants;
import edu.ucla.library.iiif.manifeststore.HTTP;
import edu.ucla.library.iiif.manifeststore.MessageCodes;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class DeleteManifestHandlerTest extends AbstractManifestHandlerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteManifestHandlerTest.class, Constants.MESSAGES);

    private static final String MANIFEST_PATH = "/{}/manifest";

    /**
     * Test the DeleteManifestHandler.
     *
     * @param aContext A testing context
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testDeleteManifestHandler(final TestContext aContext) throws IOException {
        final Async asyncTask = aContext.async();
        final int port = aContext.get(Config.HTTP_PORT);
        final String testIDPath = StringUtils.format(MANIFEST_PATH, myManifestID);

        LOGGER.debug(MessageCodes.MFS_012, myManifestID);

        myVertx.createHttpClient().delete(port, Constants.UNSPECIFIED_HOST, testIDPath, response -> {
            final int statusCode = response.statusCode();

            if (response.statusCode() == HTTP.SUCCESS_NO_CONTENT) {
                aContext.assertFalse(myS3Client.doesObjectExist(myS3Bucket, myManifestID));
                asyncTask.complete();
            } else {
                aContext.fail(LOGGER.getMessage(MessageCodes.MFS_004, HTTP.SUCCESS_NO_CONTENT, statusCode));
                asyncTask.complete();
            }
        }).end();
    }

    /**
     * Test the DeleteManifestHandler with .json-less ID.
     *
     * @param aContext A testing context
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testDeleteManifestHandlerJsonless(final TestContext aContext) throws IOException {
        final Async asyncTask = aContext.async();
        final int port = aContext.get(Config.HTTP_PORT);
        final String testIDPath = StringUtils.format(MANIFEST_PATH, myJsonlessManifestID);

        LOGGER.debug(MessageCodes.MFS_012, myJsonlessManifestID);

        myVertx.createHttpClient().delete(port, Constants.UNSPECIFIED_HOST, testIDPath, response -> {
            final int statusCode = response.statusCode();

            if (response.statusCode() == HTTP.SUCCESS_NO_CONTENT) {
                final String jsonlessId = myJsonlessManifestID + Constants.JSON_EXT;

                aContext.assertFalse(myS3Client.doesObjectExist(myS3Bucket, jsonlessId));
                asyncTask.complete();
            } else {
                aContext.fail(LOGGER.getMessage(MessageCodes.MFS_004, HTTP.SUCCESS_NO_CONTENT, statusCode));
                asyncTask.complete();
            }
        }).end();
    }

    /**
     * Confirm that a bad path request returns a 404 response.
     *
     * @param aContext A testing context
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testGetManifestHandler404(final TestContext aContext) {
        final Async asyncTask = aContext.async();
        final int port = aContext.get(Config.HTTP_PORT);
        final String testIDPath = "/testIdentifier"; // path should be: /{id}/manifest

        myVertx.createHttpClient().delete(port, Constants.UNSPECIFIED_HOST, testIDPath, response -> {
            final int statusCode = response.statusCode();

            if (response.statusCode() != HTTP.NOT_FOUND) {
                aContext.fail(LOGGER.getMessage(MessageCodes.MFS_004, HTTP.NOT_FOUND, statusCode));
            }

            asyncTask.complete();
        }).end();
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

}
