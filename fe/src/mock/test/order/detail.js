module.exports = [{
    "delay": 500,
    "response": {
        "resCode": 0,
        "msg": null,
        "data": {
            "applyId": '123123',
            "nodeDetails": [{
                "name": "锁号成功",
                "time": "2017-05-15 16:01:50",
                "desc": [{
                    label: '就诊人',
                    type: 'text',
                    value: '啊啊'
                },{
                    label: 'his系统对应单号',
                    type: 'number',
                    value: 123123
                },{
                    label: '就诊时间',
                    type: 'time',
                    value: 123123
                },{
                    label: '就诊科室',
                    type: 'text',
                    value: '一级科室 - 二级科室'
                },{
                    label: '医生职称',
                    type: 'text',
                    value: '主任医师'
                }],
                "click": true,
                "urlParam": 22222
            },{
                "name": "锁号成功",
                "time": "2017-05-15 16:01:50",
                "desc": [{
                    label: '退回用户垫付金额，退款金额',
                    type: 'money',
                    value: '40'
                },{
                    label: 'his系统对应单号',
                    type: 'number',
                    value: 123123
                },{
                    label: '就诊时间',
                    type: 'time',
                    value: 123123
                },{
                    label: '就诊科室',
                    type: 'text',
                    value: '一级科室 - 二级科室'
                },{
                    label: '医生职称',
                    type: 'text',
                    value: '主任医师'
                }],
                "click": false,
                "urlParam": 11111
            }],
            "operations": [
                "REFUND_NO",
                // "REFUND_NO_NEED_CHECK",
                // "REFUND_PAY", 
                // "REFUND_NO_CHECK", 
                // "REFUND_PAY_CHECK", 
                // "REPRINT_NO",
                // "REPRINT_DETAIL",
                "CANCEL_NOSHOW"
            ]
        }
    }
}]
