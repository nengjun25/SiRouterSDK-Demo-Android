package sirouter.sdk.siflower.com.sirouterapi;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.IFace;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WDSInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WDSScanInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WiFiAdvanceInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WiFiInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.SFException;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetDeviceDataUsageParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetDeviceRestrictParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetFreqIntergrationParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetCustomWiFiParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetDeviceDataUsageParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetDeviceRestrictParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetFreqIntergrationParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetLeaseNetParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetPasswordParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWanTypeParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWiFiAdvanceInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.WifiParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.BindRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.CommandRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.DataUsage;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.Device;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetCustomWiFiRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetDeviceDataUsageRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetDeviceRestrictRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetFreqIntergrationRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetLeaseNetRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWanTypeRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWiFiDetailRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.RemoteSessionRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetCustomWiFiRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetDeviceDataUsageRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetDeviceRestrictRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetFreqIntergrationRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetLeaseNetRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetPasswordRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWanTypeRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWiFiAdvanceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWiFiDetailRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.UnbindRet;
import sirouter.sdk.siflower.com.remotelibrary.Listener.RemoteConnectionListener;
import sirouter.sdk.siflower.com.remotelibrary.Listener.SFObjectResponseListener;
import sirouter.sdk.siflower.com.remotelibrary.Listener.SiWiFiListListener;
import sirouter.sdk.siflower.com.remotelibrary.SFClass.RouterSub;
import sirouter.sdk.siflower.com.remotelibrary.SFClass.Routers;
import sirouter.sdk.siflower.com.remotelibrary.SFUser;
import sirouter.sdk.siflower.com.remotelibrary.SiWiFiManager;
import sirouter.sdk.siflower.com.storagelibrary.FileListListener;
import sirouter.sdk.siflower.com.storagelibrary.SFStorageException;
import sirouter.sdk.siflower.com.storagelibrary.SessionConnectListener;
import sirouter.sdk.siflower.com.storagelibrary.StorageManager;
import sirouter.sdk.siflower.com.storagelibrary.jcifs.smb.SmbFile;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    private MainActivity mainActivity;

    private SFUser mUser;

    private Routers routers;

    private Button loginExtra;


    private Button bindSiRouter;
    private Button unbindSiRouter;

    private Button getWifiObserve;
    private Button getWiFiAdvanceObserve;
    private Button getCustomWiFiFace;
    private Button getWDSInfo;
    private Button getWDSScan;
    private Button getWDSRelIp;
    private Button getDeviceRestrict;
    private Button getDeviceDataUsage;
    private Button getSiRouterDeviceDetail;
    private Button getFreqIntergration;
    private Button getLeaseNet;
    private Button getRouters;
    private Button getGatewayIp;
    private Button getWanTypeObserve;

    private Button setFreqIntergration;
    private Button setDeviceDataUsage;
    private Switch setFreqIntergration_switch;
    private Button setLeaseNet;
    private Button setWanType;
    private Button setWiFiAdvance;
    private Button setWiFi;
    private Button setAdminPassword;
    private EditText setOldpwd;
    private EditText setNewpwd;
    private Button setDeviceRestrict;
    private Button setCustomWiFi;


    private Button localGetFile;
    private Button localRenameFile;
    private Button localPasteFile;
    private Button createSession;
    private Button remoteGetFile;
    private Button deleteSession;
    private StorageManager storageManager;

    private static final String TAG = "mainActivity";

    private int flag;
    private enum SessionStatus{
        no_connect,connecting,connected
    }

    private SessionStatus sessionStatus;

    private String appKey = "c20ad4d76fe97759aa27a0c99bff6710";
    private String appSecret = "864850023f299568b353d21e55c6c892";
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SiWiFiManager.init(this, appKey, appSecret);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("This is ProgressDialog");
        progressDialog.setMessage("Loading...");
        mUser = SFUser.getCacheUser(this);
        mainActivity = this;
        sessionStatus = SessionStatus.no_connect;
        storageManager = new StorageManager("192.168.4.1");

        loginExtra = findViewById(R.id.loginExtra);
        editText = findViewById(R.id.editText);
        setOldpwd = findViewById(R.id.setOldpwd);
        setNewpwd = findViewById(R.id.setNewpwd);
        bindSiRouter = findViewById(R.id.bindSiRouter);
        unbindSiRouter = findViewById(R.id.unbindSiRouter);
        getWifiObserve = findViewById(R.id.getWifiObserve);
        getWiFiAdvanceObserve = findViewById(R.id.getWiFiAdvanceObserve);
        getCustomWiFiFace = findViewById(R.id.getCustomWiFiIFace);
        getWDSInfo = findViewById(R.id.getWDSInfo);
        getWDSScan = findViewById(R.id.getWDSScan);
        getWDSRelIp = findViewById(R.id.getWDSRelIp);
        getSiRouterDeviceDetail = findViewById(R.id.getSiRouterDeviceDetail);
        getDeviceDataUsage = findViewById(R.id.getDeviceDataUsage);
        getDeviceRestrict = findViewById(R.id.getDeviceRestrict);
        getFreqIntergration = findViewById(R.id.getFreqIntergration);
        getLeaseNet = findViewById(R.id.getLeaseNet);
        getRouters = findViewById(R.id.getRouters);
        getWanTypeObserve = findViewById(R.id.getWanTypeObserve);
        getGatewayIp = findViewById(R.id.getGatewayIp);

        setFreqIntergration = findViewById(R.id.setFreqIntergration);
        setFreqIntergration_switch = findViewById(R.id.setFreqIntergration_switch);
        setDeviceDataUsage = findViewById(R.id.setDeviceDataUsage);
        setLeaseNet = findViewById(R.id.setLeaseNet);
        setWanType = findViewById(R.id.setWanType);
        setAdminPassword = findViewById(R.id.setAdminPassword);
        setDeviceRestrict = findViewById(R.id.setDeviceRestrict);
        setCustomWiFi = findViewById(R.id.setCustomWiFi);
        setWiFi = findViewById(R.id.setWiFi);
        setWiFiAdvance = findViewById(R.id.setWiFiAdvance);

        localGetFile = findViewById(R.id.local_get_file);
        localPasteFile = findViewById(R.id.local_paste_file);
        localRenameFile = findViewById(R.id.local_rename);

        createSession = findViewById(R.id.create_session);
        remoteGetFile  = findViewById(R.id.remote_get_file);
        deleteSession = findViewById(R.id.delete_channel);


        if (mUser != null && !mUser.getLoginkey().equals("")) {
            SFUser.loginByKey(this, mUser.getLoginkey(), new SFObjectResponseListener<SFUser>() {
                @Override
                public void onSuccess(SFUser sfUser) {
                    Log.e(TAG, "login success" + new Gson().toJson(sfUser));
                    MainActivity.this.mUser = sfUser;
                    if (sfUser.getBinder() != null) {
                        if (sfUser.getBinder().size() != 0) {
                            Log.e(TAG, "not zero" + new Gson().toJson(sfUser.getBinder()));
                            routers = sfUser.getBinder().get(0);
                        }
                    }

                    SiWiFiManager.getInstance().createRemoteConnection(sfUser, new RemoteConnectionListener() {
                        @Override
                        public void onConnectSuccess() {
                            Log.e(TAG, "on connection success" + Thread.currentThread().getName());
//                                Toast.makeText(MainActivity.this, "on connection success", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onConnectionClose(int code, String reason) {
                            Log.e(TAG, "on connection close" + Thread.currentThread().getName());
                            //  Toast.makeText(MainActivity.this, "on connection close", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Exception ex) {
                            Log.e(TAG, "on Failure");
                        }
                    });
                }

                @Override
                public void onError(SFException ex) {
                    Log.e(TAG, "登录失败，请检查AppSecret和AppKey");
                }
            });
        }

        bind();
        unbind();
        getWiFi();
        getWanType();
        getDevice();
        getWanType();
        getFreqIntergration();
        getLeaseNet();
        getWiFiAdvance();
        getCustomWiFiFace();
        getDeviceRestrict();
        getDeviceDataUsage();
        getWDSRelIp();
        getWDSInfo();
        getWDSRelIp();
        getRouters();
        loginExtra();
        getWDSScan();
        setFreqIntergration();
        setWiFi();
        setCustomWifi();
        setWiFiAdvance();
        setDeviceDataUsage();
        setDeviceRestrict();
        setLeaseNet();
        setAdminPwd();
        setWanType();
        getGateWayIp();
        localGetFile();
        localRenameFile();
        localPasteFile();
        createSession();
        remoteGetFile();
        deleteSession();
    }

    public boolean flagCondition() {
        if (flag == 0) {
            if (mUser == null) {
                Toast.makeText(MainActivity.this, "用户未登录", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (routers == null) {
                Toast.makeText(MainActivity.this, "未绑定路由器", Toast.LENGTH_SHORT).show();
                return false;
            }
            progressDialog.show();
            return true;
        } else if (flag == 1||flag == 2) {
            if (mUser != null&& routers != null) {
                Toast.makeText(MainActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (mUser == null&& routers != null) {
                Toast.makeText(MainActivity.this, "用户未登录", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (mUser != null && routers == null) {
                Toast.makeText(MainActivity.this, "未绑定路由器", Toast.LENGTH_SHORT).show();
                return false;
            }
            return false;
        }
        return false;
    }

    private void bind(){
        bindSiRouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    Toast.makeText(MainActivity.this, "用户未登录", Toast.LENGTH_SHORT).show();
                    return;
                }



                SiWiFiManager.getInstance().bindSiRouter(MainActivity.this, LocalApi.DEFAULT_APP_API_VERSION, mUser, new SingleObserver<BindRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(BindRet bindRet) {
                        Log.e(TAG, "bind success ");
                        Toast.makeText(MainActivity.this, new Gson().toJson(bindRet), Toast.LENGTH_SHORT).show();
                        SFUser.loginByKey(MainActivity.this, mUser.getLoginKeyExtra(), new SFObjectResponseListener<SFUser>() {
                            @Override
                            public void onSuccess(SFUser sfUser) {
                                List<Routers> routers = sfUser.getBinder();
                            }

                            @Override
                            public void onError(SFException e) {

                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "bind error " + e.getMessage());
                    }
                });
            }
        });
    }

    private void unbind(){
        unbindSiRouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SiWiFiManager.getInstance().unbindSiRouter(routers, mUser, new SingleObserver<UnbindRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(UnbindRet unbindRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(unbindRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void getWiFi(){
        getWifiObserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalApi mLocalApi = new LocalApi(LocalApi.DEFAULT_APP_API_VERSION);
                mLocalApi.setmLocalIp("192.168.4.1");
                mLocalApi.setAdminPassword("admin");
                GetWiFiDetailParam param = new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION);

                mLocalApi.executeApiWithSingleResponse(param, GetWiFiDetailRet.class).subscribe(new SingleObserver<GetWiFiDetailRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {
                        Log.e(TAG,"get wifi success "+new Gson().toJson(getWiFiDetailRet));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"get wifi error "+e.getMessage());
                    }
                });


                /*if(!flagCondition()) {
                    return;
                }*/
                /*routers = new Routers();
                RouterSub sub = new RouterSub();
                sub.setObjectId("548978976");
                routers.setSub(sub);
                routers.setObjectId("13123131");
                mUser = new SFUser();
                mUser.setObjectId("323132131");

                SiWiFiManager.getInstance().getWifiObserve(routers, mUser, new SingleObserver<List<WiFiInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<WiFiInfo> wiFiInfos) {
                        progressDialog.dismiss();
                        Log.e(TAG, "wifiinfos: " + new Gson().toJson(wiFiInfos));
                        Toast.makeText(MainActivity.this, new Gson().toJson(wiFiInfos), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        if(e instanceof SFException) {
                            Log.e(TAG, "code = " + ((SFException) e).getCode() + " msg = " + e.getMessage());
                        }else {
                            Log.e(TAG,  " msg = " + e.getMessage());
                        }
                    }
                });*/


            }
        });
    }

    private void loginExtra(){
        loginExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SFUser.loginByExtra(mainActivity, editText.getText().toString(), new SFObjectResponseListener<SFUser>() {
                    @Override
                    public void onSuccess(SFUser sfUser) {
                        Log.e(TAG, "login success" + new Gson().toJson(sfUser));
                        Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                        MainActivity.this.mUser = sfUser;
                        if (sfUser.getBinder() != null) {
                       if (sfUser.getBinder().size() != 0) {
                            Log.e(TAG, "not zero" + new Gson().toJson(sfUser.getBinder()));
                            routers = sfUser.getBinder().get(0);
                          }
                  }
                    SiWiFiManager.getInstance().createRemoteConnection(sfUser, new RemoteConnectionListener() {
                @Override
                        public void onConnectSuccess() {
                       flag = 0;
                          Log.e(TAG, "on connection success");
                       }

                           @Override
                     public void onConnectionClose(int code, String reason) {
                       flag = 1;
                                             Log.e(TAG, "on connection close");
                             }

                                @Override
                     public void onFailure(Exception ex) {
                                                     flag = 2;
                                                       Log.e(TAG, "on Failure");
                                                  }
            });

                    }

                    @Override
                    public void onError(SFException ex) {
                        Log.e(TAG, "login fail code "+ ex.getCode()+" msg "+ex.getMessage());
                    }
                });
            }
        });
    }

    private void getCustomWiFiFace(){
        getCustomWiFiFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SiWiFiManager.getInstance().getCustomWiFiIFace(routers, mUser, new SingleObserver<GetCustomWiFiRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetCustomWiFiRet getCustomWiFiRet) {
                        progressDialog.dismiss();
                        Log.e(TAG, "getCustomWiFiRet" + new Gson().toJson(getCustomWiFiRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getCustomWiFiRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "getCustomWiFiRet" + e.getMessage());
                    }
                });
            }
        });
    }

    private void getWDSInfo(){
        getWDSInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SiWiFiManager.getInstance().getWDSInfo(routers, mUser, new SingleObserver<List<WDSInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<WDSInfo> wdsInfos) {
                        progressDialog.dismiss();
                        Log.e(TAG, "wdsinfos: " + new Gson().toJson(wdsInfos));
                        Toast.makeText(MainActivity.this, new Gson().toJson(wdsInfos), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "wdsinfos: " + e.getMessage());
                    }
                });
            }
        });
    }

    private void getDevice(){
        getSiRouterDeviceDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()){
                    return;
                }

                SiWiFiManager.getInstance().getSiRouterDeviceDetail(routers, mUser, new SingleObserver<List<Device>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Device> devices) {
                        progressDialog.dismiss();
                        Log.e(TAG, "devices: " + new Gson().toJson(devices));
                        Toast.makeText(MainActivity.this, new Gson().toJson(devices), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "devices: " + e.getMessage());
                    }
                });
            }
        });
    }

    private void getDeviceDataUsage(){
        getDeviceDataUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                GetDeviceDataUsageParam param = new GetDeviceDataUsageParam("V10");
                param.setMac("A0_86_C6_9D_28_7D");
                SiWiFiManager.getInstance().getDeviceDataUsage(routers, mUser, param, new SingleObserver<GetDeviceDataUsageRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetDeviceDataUsageRet getDeviceDataUsageRet) {
                        progressDialog.dismiss();
                        Log.e(TAG, "getDeviceDataUsageRet" + new Gson().toJson(getDeviceDataUsageRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getDeviceDataUsageRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "getDeviceDataUsageRet" + e.getMessage());
                    }
                });
            }
        });
    }

    private void getLeaseNet(){
        getLeaseNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SiWiFiManager.getInstance().getLeaseNet(routers, mUser, new SingleObserver<GetLeaseNetRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetLeaseNetRet getLeaseNetRet) {
                        progressDialog.dismiss();
                        Log.e(TAG, "getLeaseNet" + new Gson().toJson(getLeaseNetRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getLeaseNetRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "getLeaseNet" + e.getMessage());
                    }
                });
            }
        });
    }

    private void getWDSScan(){
        getWDSScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()){
                    return;
                }

                String band = "2.4G";
                SiWiFiManager.getInstance().getWDSScan(routers, mUser, band, new SingleObserver<List<WDSScanInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<WDSScanInfo> wdsScanInfos) {
                        progressDialog.dismiss();
                        Log.e(TAG, "wdsScanInfos: " + new Gson().toJson(wdsScanInfos));
                        Toast.makeText(MainActivity.this, new Gson().toJson(wdsScanInfos), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "wdsScanInfos: " + e.getMessage());
                    }
                });
            }
        });
    }

    private void getWDSRelIp(){
        getWDSRelIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                String band = "2.4G";
                SiWiFiManager.getInstance().getWDSRelIp(routers, mUser, band, new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        progressDialog.dismiss();
                        Log.e(TAG, "s: " + new Gson().toJson(s));
                        Toast.makeText(MainActivity.this, new Gson().toJson(s), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "s: " + e.getMessage());
                    }
                });
            }
        });
    }

    private void getDeviceRestrict(){
        getDeviceRestrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()){
                    return;
                }
                GetDeviceRestrictParam param = new GetDeviceRestrictParam("V10");
                param.setMac("A0_86_C6_9D_28_7D");
                SiWiFiManager.getInstance().getDeviceRestrict(routers, mUser, param, new SingleObserver<GetDeviceRestrictRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetDeviceRestrictRet getDeviceRestrictRet) {
                        progressDialog.dismiss();
                        Log.e(TAG, "getDeviceRestrictRet" + new Gson().toJson(getDeviceRestrictRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getDeviceRestrictRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "getDeviceRestrictRet" + e.getMessage());
                    }
                });
            }
        });
    }

    private void getRouters(){
        getRouters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SiWiFiManager.getInstance().getRouters(mUser, new SiWiFiListListener<Routers>() {
                    @Override
                    public void onSuccess(List<Routers> list) {
                        progressDialog.dismiss();
                        Log.e(TAG, "  getRouters" + new Gson().toJson(list));
                        Toast.makeText(MainActivity.this, new Gson().toJson(list), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int i, String s) {
                        progressDialog.dismiss();
                        Log.e(TAG, " getRouters" + s);
                    }
                });
            }
        });
    }

    private void getFreqIntergration(){
        getFreqIntergration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                GetFreqIntergrationParam param = new GetFreqIntergrationParam("V17");

                SiWiFiManager.getInstance().getFreqIntergration(routers, mUser, param, new SingleObserver<GetFreqIntergrationRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetFreqIntergrationRet getFreqIntergrationRet) {
                        progressDialog.dismiss();
                        Log.e(TAG, " getFreqIntergrationRet" + new Gson().toJson(getFreqIntergrationRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getFreqIntergrationRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG, " getFreqIntergrationRet" + e.getMessage());
                    }
                });
            }
        });
    }

    private void getWanType() {
        getWanTypeObserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flagCondition()) {
                    return;
                }
                SiWiFiManager.getInstance().getWanTypeObserve(routers, mUser, new SingleObserver<GetWanTypeRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetWanTypeRet getWanTypeRet) {
                        progressDialog.dismiss();
                        Log.e(TAG, "getWanTypeRet)" + new Gson().toJson(getWanTypeRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getWanTypeRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "getWanTypeRet)" + e.getMessage());
                    }
                });
            }
        });
    }

    private void getWiFiAdvance(){
        getWiFiAdvanceObserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SiWiFiManager.getInstance().getWifiAdvanceObserve(routers, mUser, new SingleObserver<List<WiFiAdvanceInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<WiFiAdvanceInfo> wiFiAdvanceInfos) {
                        progressDialog.dismiss();
                        Log.e(TAG, "wiFiAdvanceInfos: " + new Gson().toJson(wiFiAdvanceInfos));
                        Toast.makeText(MainActivity.this, new Gson().toJson(wiFiAdvanceInfos), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "wiFiAdvanceInfos: " + e.getMessage());
                    }
                });
            }
        });
    }

    private void getGateWayIp(){
        getGatewayIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    Toast.makeText(MainActivity.this, "用户未登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (routers == null) {
                    Toast.makeText(MainActivity.this, "未绑定路由器", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e(TAG, "getGateWayIp" + (SiWiFiManager.getInstance().getGatewayIp(mainActivity)));
                Toast.makeText(MainActivity.this, new Gson().toJson((SiWiFiManager.getInstance().getGatewayIp(mainActivity))), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFreqIntergration(){
        setFreqIntergration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SetFreqIntergrationParam param = new SetFreqIntergrationParam("V17");
                if (setFreqIntergration_switch.isChecked()) {
                    param.setEnable(1);
                } else {
                    param.setEnable(0);
                }
                Toast.makeText(mainActivity, "开始请求", Toast.LENGTH_SHORT).show();
                SiWiFiManager.getInstance().setFreqIntergration(routers, mUser, param, new SingleObserver<SetFreqIntergrationRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(SetFreqIntergrationRet setFreqIntergrationRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setFreqIntergrationRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setDeviceDataUsage() {
        setDeviceDataUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flagCondition()) {
                    return;
                }
                SetDeviceDataUsageParam param = new SetDeviceDataUsageParam("V14");
                param.setMac("A0_86_C6_9D_28_7D");
                List<DataUsage> list = new ArrayList<DataUsage>();
                DataUsage dataUsage = new DataUsage();
                dataUsage.setCount(100);
                dataUsage.setType(1);
                dataUsage.setWeek("1,7");
                list.add(dataUsage);
                param.setSetlist(list);
                param.setChange(100);
                param.setUsageenable(1);
                SiWiFiManager.getInstance().setDeviceDataUsage(routers, mUser, param).subscribe(new SingleObserver<SetDeviceDataUsageRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetDeviceDataUsageRet setDeviceDataUsageRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setDeviceDataUsageRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setLeaseNet(){
        setLeaseNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SetLeaseNetParam param = new SetLeaseNetParam("V14");
                param.setEnable(true);
                param.setSsid("liuxiaopeng-5G");
                param.setLimitdownload(100);
                SiWiFiManager.getInstance().setLeaseNet(routers, mUser, true, "liuxiaopeng-5G", 100, new SingleObserver<SetLeaseNetRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetLeaseNetRet setLeaseNetRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setLeaseNetRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setWanType(){
        setWanType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SetWanTypeParam param = new SetWanTypeParam("V14");
                param.setType(0);
                SiWiFiManager.getInstance().setWanType(routers, mUser, param, new SingleObserver<SetWanTypeRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetWanTypeRet setWanTypeRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setWanTypeRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setAdminPwd(){
        setAdminPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SetPasswordParam param = new SetPasswordParam("V14");
                param.setOldpwd(setOldpwd.getText().toString());
                param.setNewpwd(setNewpwd.getText().toString());
                SiWiFiManager.getInstance().setAdminPassword(routers, mUser, setOldpwd.getText().toString(), setNewpwd.getText().toString(), new SingleObserver<SetPasswordRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetPasswordRet setPasswordRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setPasswordRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setCustomWifi(){
        setCustomWiFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SetCustomWiFiParam param = new SetCustomWiFiParam("V14");
                List<IFace> list = new ArrayList<IFace>();
                IFace iFace = new IFace();
                iFace.setBand("2.4G");
                iFace.setRemainingtime(0);
                iFace.setSsid("liuxiaopeng-2.4G");
                iFace.setEnable(true);
                iFace.setLimittimetype(0);
                iFace.setOpen(false);
                iFace.setLimitupload(10);
                iFace.setPeriodicaltime("1-6");
                iFace.setKey("123456789");
                iFace.setLimitdownload(10);
                iFace.setLocalaccess(false);
                iFace.setLimittime(false);
                list.add(iFace);
                param.setIfaces(list);
                SiWiFiManager.getInstance().setCustomWiFi(routers, mUser, param, new SingleObserver<SetCustomWiFiRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetCustomWiFiRet setCustomWiFiRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setCustomWiFiRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setDeviceRestrict(){
        setDeviceRestrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                SetDeviceRestrictParam param = new SetDeviceRestrictParam("V14");
                param.setMac("A0_86_C6_9D_28_7D");
                param.setSocial(0);
                param.setVideo(0);
                param.setGame(1);
                param.setRestrictenable(0);
                SiWiFiManager.getInstance().setDeviceRestrict(routers, mUser, param).subscribe(new SingleObserver<SetDeviceRestrictRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetDeviceRestrictRet setDeviceRestrictRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setDeviceRestrictRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setWiFi() {
        setWiFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<WifiParam> list = new ArrayList<WifiParam>();
                WifiParam wifiParam24 = new WifiParam();
                wifiParam24.enable = 1;
                wifiParam24.encryption = "psk2+ccmp";
                wifiParam24.signalmode = 0;
                wifiParam24.channel = 6;
                wifiParam24.password = "12345678";
                wifiParam24.oldssid = "siwifi-7fb8-2.4G";
                wifiParam24.newssid = "Siflower123-2.4G";
                WifiParam wifiParam5 = new WifiParam();
                wifiParam5.enable = 1;
                wifiParam5.encryption = "psk2+ccmp";
                wifiParam5.signalmode = 0;
                wifiParam5.channel = 161;
                wifiParam5.password = "12345678";
                wifiParam5.oldssid = "siwifi-7fbc";
                wifiParam5.newssid = "Siflower123";
                list.add(wifiParam24);
                list.add(wifiParam5);

                LocalApi mLocalApi = new LocalApi(LocalApi.DEFAULT_APP_API_VERSION);
                mLocalApi.setmLocalIp("192.168.4.1");
                mLocalApi.setAdminPassword("admin");
                SetWiFiDetailParam setWiFiDetailParam = new SetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION);
                setWiFiDetailParam.setSetting(new Gson().toJson(list));
                Log.e(TAG," wifi param "+new Gson().toJson(setWiFiDetailParam));
                mLocalApi.executeApiWithSingleResponse(setWiFiDetailParam,SetWiFiDetailRet.class).subscribe(new SingleObserver<SetWiFiDetailRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetWiFiDetailRet ret) {
                        Log.e(TAG,"set wifi success"+new Gson().toJson(ret));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"set wifi fail "+e.getMessage());
                    }
                });

                /*SiWiFiManager.getInstance().setWiFi(routers, mUser, list, new SingleObserver<SetWiFiDetailRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetWiFiDetailRet setWiFiDetailRet) {
                        Log.e(TAG,"set wifi success"+new Gson().toJson(setWiFiDetailRet));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"set wifi fail "+e.getMessage());
                    }
                });*/
            }
        });
    }

    private void setWiFiAdvance(){
        setWiFiAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagCondition()) {
                    return;
                }
                List<SetWiFiAdvanceInfo> list = new ArrayList<SetWiFiAdvanceInfo>();

                SetWiFiAdvanceInfo param24 = new SetWiFiAdvanceInfo();
                param24.country = "CN";
                param24.rts = 0;
                param24.distance = 0;
                param24.enable = 1;
                param24.encryption = "psk2+ccmp";
                param24.fragment = 0;
                param24.signalmode = 0;

                SetWiFiAdvanceInfo param5 = new SetWiFiAdvanceInfo();
                param5.country = "CN";
                param5.rts = 0;
                param5.distance = 0;
                param5.enable = 1;
                param5.encryption = "psk2+ccmp";
                param5.fragment = 0;
                param5.signalmode = 0;

                list.add(param24);
                list.add(param5);

                SiWiFiManager.getInstance().setWiFiAdvance(routers, mUser, list, new SingleObserver<SetWiFiAdvanceRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetWiFiAdvanceRet setWiFiAdvanceRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setWiFiAdvanceRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void localGetFile(){
        localGetFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storageManager.getSMBFileList(StorageManager.baseUri, new FileListListener<SmbFile>() {
                    @Override
                    public void onGetFile(List<SmbFile> sfSmbFile) {
                        Log.e(TAG," sfs file size "+sfSmbFile.size());
                    }

                    @Override
                    public void onError(SFStorageException e) {
                        Log.e(TAG," sfs file size error "+e.getMessage());
                    }
                });
            }
        });
    }

    private void localRenameFile(){
        localRenameFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storageManager.renameSmbFile(StorageManager.baseUri, "weeklyreport.xls", "weeklyreport111.xls", new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            }
        });
    }

    private void localPasteFile(){
        localPasteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> names = new ArrayList<>();
                names.add("weeklyreport.xls");
                storageManager.copySmbFile(names, StorageManager.baseUri, StorageManager.baseUri + "newfolder/", new FileListListener<SmbFile>() {
                    @Override
                    public void onGetFile(List<SmbFile> sfSmbFile) {
                        Log.e(TAG,"success ");
                    }

                    @Override
                    public void onError(SFStorageException e) {
                        Log.e(TAG,"copy error "+e.getMessage());
                    }
                });
            }
        });
    }

    private void createSession(){
        createSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flagCondition()){
                    return;
                }

                SiWiFiManager.getInstance().getRemoteSession(routers, mUser, new SingleObserver<RemoteSessionRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(RemoteSessionRet remoteSessionRet) {
                        progressDialog.dismiss();
                        if (remoteSessionRet.getCode() == 0) {
                            storageManager.setSessionListener(new SessionConnectListener() {
                                @Override
                                public void onConnecting() {
                                    Log.e(TAG,"connecting");
                                    sessionStatus = SessionStatus.connecting;
                                }

                                @Override
                                public void onConnected() {
                                    Log.e(TAG,"connected");
                                    sessionStatus = SessionStatus.connected;
                                }

                                @Override
                                public void onDisconnected() {
                                    Log.e(TAG,"disconnected");
                                    sessionStatus = SessionStatus.no_connect;
                                }
                            });
                            storageManager.createNewChannel(remoteSessionRet.getUrl(),remoteSessionRet.getSession());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.e(TAG,"get remote session error "+e.getMessage());
                    }
                });
            }
        });
    }

    private void remoteGetFile(){
        remoteGetFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sessionStatus == SessionStatus.connected) {
                    storageManager.getSMBFileListRemote(StorageManager.baseUri, new FileListListener<SmbFile>() {
                        @Override
                        public void onGetFile(List<SmbFile> sfSmbFile) {
                            Log.e(TAG, " sfs file size " + sfSmbFile.size());
                        }

                        @Override
                        public void onError(SFStorageException e) {
                            Log.e(TAG, " sfs file size error " + e.getMessage());
                        }
                    });
                }else {
                    Toast.makeText(MainActivity.this,"session未连接",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteSession(){
        deleteSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storageManager.deleteChannel();
            }
        });
    }

}


