
package edu.ucla.library.iiif.manifeststore.handlers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
public class GetManifestHandlerTest extends AbstractManifestHandlerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetManifestHandlerTest.class, Constants.MESSAGES);

    /**
     * Test the GetManifestHandler.
     *
     * @param aContext A testing context
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testGetManifestHandler(final TestContext aContext) throws IOException {
        final String expectedManifest = StringUtils.read(MANIFEST_FILE);
        final Async asyncTask = aContext.async();
        final int port = aContext.get(Config.HTTP_PORT);
        final String testIDPath = "/manifests/" + myManifestID;

        LOGGER.debug(MessageCodes.MFS_008, myManifestID);

        myVertx.createHttpClient().getNow(port, Constants.UNSPECIFIED_HOST, testIDPath, response -> {
            final int statusCode = response.statusCode();

            if (response.statusCode() == HTTP.OK) {
                response.bodyHandler(body -> {
                    final String foundManifest = body.toString(StandardCharsets.UTF_8);

                    // Check that what we retrieve is the same as what we stored
                    aContext.assertEquals(expectedManifest, foundManifest);
                    asyncTask.complete();
                });
            } else {
                aContext.fail(LOGGER.getMessage(MessageCodes.MFS_003, HTTP.OK, statusCode));
                asyncTask.complete();
            }
        });
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
        final String testIDPath = "/testIdentifier"; // path should start with: /manifests

        myVertx.createHttpClient().getNow(port, Constants.UNSPECIFIED_HOST, testIDPath, response -> {
            final int statusCode = response.statusCode();

            if (response.statusCode() != HTTP.NOT_FOUND) {
                aContext.fail(LOGGER.getMessage(MessageCodes.MFS_004, HTTP.NOT_FOUND, statusCode));
            }

            asyncTask.complete();
        });
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

}
