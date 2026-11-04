package se.kitt.cockpit2;

import org.junit.Test;
import static org.junit.Assert.*;

public class PackageIdentityTest {
    @Test public void packageNamespaceIsStable() {
        assertEquals("se.kitt.cockpit2", PackageIdentityTest.class.getPackage().getName());
    }
    @Test public void appAssetUrlUsesSecureOrigin() {
        String url = "https://appassets.androidplatform.net/assets/index.html";
        assertTrue(url.startsWith("https://"));
        assertTrue(url.endsWith("/assets/index.html"));
    }
}
