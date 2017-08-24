package com.benmu.framework;

import java.util.HashMap;

/**
 * Created by Carry on 2017/8/23.
 */

public class BMInitConfig {

    private HashMap<String, String> mEnvs;

    private BMInitConfig() {
    }

    public HashMap<String, String> getmEnvs() {
        return mEnvs;
    }

    public void setmEnvs(HashMap<String, String> mEnvs) {
        this.mEnvs = mEnvs;
    }

    public static class Builder {
        HashMap<String, String> mCustomerEnv;

        public Builder setCustomerEnv(HashMap<String, String> mCustomerEnv) {
            this.mCustomerEnv = mCustomerEnv;
            return this;
        }

        public BMInitConfig build() {
            BMInitConfig initConfig = new BMInitConfig();
            initConfig.mEnvs = this.mCustomerEnv;
            return initConfig;
        }
    }
}
