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

