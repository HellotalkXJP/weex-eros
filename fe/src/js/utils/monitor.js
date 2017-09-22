const axios = weex.requireModule('bmAxios')
const APPNAMR = 'fe_app_jyt'
const MONITORURL = '/proxy/receive'  // 待定

function _monitorTimer(host, name, alias, time, path){
    if(!name){
        return;
    }
    axios.fetch({
        method: 'POST',
        url: MONITORURL,
        data: {
            app: APPNAMR,
            name: name,
            alias: alias || '',
            metricType: 'TIMER',
            host: host,
            tags: {
                path: path && path
            },
            val: time || 1
        }
    });
}

function _monitorCount(host, name, alias, path){
    if(!name){
        return;
    }
    axios.fetch({
        method: 'post',
        url: MONITORURL,
        data: {
            app: APPNAMR,
            name: name,
            alias: alias || '',
            metricType: 'COUNTER_DELTA',
            host: host,
            tags: {
                path: path && path
            },
            val: 1
        }
    });
}

module.exports = {
    timer: _monitorTimer,
    count: _monitorCount
};
