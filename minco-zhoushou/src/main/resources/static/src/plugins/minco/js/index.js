//全局配置
var config = {
    //控制 按钮以及查询文本框 按钮的
    size: 'small',
    pageSize: 10,
    showPermissionUrl: "/backUserPermission/show/",
    updatePermissionUrl: "/backUserPermission/update/",
    backUserPageUrl: "/backUser/init"
}

const my = {
    /**
     * 转化页面初始化化得到的数据
     */
    conversionInitDataFn(data) {
        console.log("conversionInitDataFn----------start---------")
        let aa = data.replace(/&quot;/g, '"');
        console.log(aa)
        // JSON.parse(jsonstr); //可以将json字符串转换成json对象
        // JSON.stringify(jsonobj); //可以将json对象转换成json对符串
        console.log("conversionInitDataFn----------start---------")
        return JSON.parse(aa);
    },
    /**
     *  不带header列表页面获取表格高度
     *  top    50
     * tool 65
     * query 60 + border 3 + center margin的20
     * bottom 55
     */
    getHeightOfListFn() {
        var temp = document.body.clientHeight;
        temp = temp - 50 - 83 - 55;
        console.log("屏幕高度" + temp + "计算后表格高度" + temp);
        return temp;
    },
    /**
     * 带header列表页面获取表格高度
     */
    getHeightOfHaveHeaderListFn() {
        var temp = document.body.clientHeight;
        temp = temp - 50 - 65 - 83 - 55;
        console.log("屏幕高度" + temp + "计算后表格高度" + temp);
        return temp;
    },
    setTableHeightOfDynamicsFn() {
        if (my.isnullFn(document.getElementById("main_list"))) {
            return;
        }
        var height = document.body.clientHeight;
        console.log("屏幕高度" + height);
        var header = document.getElementById("main_list_header");
        if (my.isNotNullFn(header)) {
            height = height - header.clientHeight;
            console.log("---header高度" + header.clientHeight)
        }
        var query = document.getElementById("main_list_center_query");
        if (my.isNotNullFn(query)) {
            height = height - query.clientHeight;
            console.log("---查询区高度" + query.clientHeight)
        }
        // -top -center - bottom
        height = height - 50 - 20 - 55;
        console.log("---计算后的高度" + height);
        var aaa = height + "px";
        document.getElementById("main_list_table").style.setProperty("height", aaa);
    },
    /**
     * 判断对象是否为空
     * @param str
     * @returns {boolean}
     */
    isnullFn(str) {
        return str == null || str == undefined || str == '' || str == 'undefined' || str == 'null';
    },
    isNotNullFn(str) {
        return !my.isnullFn(str);
    },

    jumpPage(menu){
        $("#mid_content").load(menu.apiPath);
        //设置页面title
        if (menu.label) {
            window.document.title = "管理系统---" + menu.label;
        }
    }

}

var firstFire = null;
window.onresize = function () {
    if (firstFire === null) {
        firstFire = setTimeout(function () {
            firstFire = null;
            my.setTableHeightOfDynamicsFn();
        }, 100);
    }
}
