package se.kitt.cockpit2;
import android.app.Activity;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.webkit.*;
import android.net.Uri;
import androidx.webkit.WebViewAssetLoader;
public class MainActivity extends Activity {
 private WebView web; private PermissionRequest pending;
 @Override public void onCreate(Bundle b){super.onCreate(b);web=new WebView(this);setContentView(web);
 web.getSettings().setJavaScriptEnabled(true);web.getSettings().setDomStorageEnabled(true);
 WebViewAssetLoader loader=new WebViewAssetLoader.Builder().addPathHandler("/assets/",new WebViewAssetLoader.AssetsPathHandler(this)).build();
 web.setWebViewClient(new WebViewClient(){@Override public WebResourceResponse shouldInterceptRequest(WebView v,WebResourceRequest r){return loader.shouldInterceptRequest(r.getUrl());}});
 web.setWebChromeClient(new WebChromeClient(){@Override public void onPermissionRequest(PermissionRequest r){runOnUiThread(()->{if(checkSelfPermission(Manifest.permission.RECORD_AUDIO)==PackageManager.PERMISSION_GRANTED){r.grant(new String[]{PermissionRequest.RESOURCE_AUDIO_CAPTURE});}else{pending=r;requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},100);}});}});
 web.loadUrl("https://appassets.androidplatform.net/assets/index.html");}
 @Override public void onRequestPermissionsResult(int code,String[] permissions,int[] results){super.onRequestPermissionsResult(code,permissions,results);if(code==100&&pending!=null){if(results.length>0&&results[0]==PackageManager.PERMISSION_GRANTED)pending.grant(new String[]{PermissionRequest.RESOURCE_AUDIO_CAPTURE});else pending.deny();pending=null;}}
 @Override public void onDestroy(){if(web!=null)web.destroy();super.onDestroy();}
}