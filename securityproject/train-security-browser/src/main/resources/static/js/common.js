$(function () {
    // 短信登录
    $(".mobile-icon-a").on("click", function () {
        $(".form-code").hide();
        $(".form-sms").show();
        $(".password-icon-a").css("display", "inline-block");
        $(".mobile-icon-a").css("display", "none");
    });
    // 密码登录
    $(".password-icon-a").on("click", function () {
        $(".form-code").show();
        $(".form-sms").hide();
        $(".password-icon-a").css("display", "none");
        $(".mobile-icon-a").css("display", "inline-block");
    });
});

layui.use(['form', 'layer','laydate'], function(){
    var form = layui.form,
        layer = layui.layer,
        laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#start' //指定元素
    });

    //执行一个laydate实例
    laydate.render({
        elem: '#end' //指定元素
    });

    //监听提交
    form.on('submit(search)', function(data){
        console.log(getUrls())
        search_form(data.field);
        return false;
    });

});

const  RequestUrl="http://localhost:8081";

/**
 * layui表单提交
 * @param args
 * @param data
 */
function form_submit(args,data) {
    //监听提交
    $.ajax({
        type: args.formType,
        contentType: "application/json",
        url: args.url,
        data: JSON.stringify(data),
        success: function () {
            layer.alert(args.title+"成功", {icon: 6}, function () {
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
            });
        },
        error: function () {
            layer.alert(args.title+"失败", {icon: 5})
        }
    });
}

/**
 * 状态改变，停用和 启用
 * @param obj
 * @param args
 */
function changeStatus(obj, args) {
    layer.confirm('确认要' + args.title + '吗？', function (index) {
        $.ajax({
            type: args.formType,
            url: args.url,
            timeout: args.timeout,
            success: function () {
                $(obj).attr('title', args.callback)
                $(obj).find('i').html(args.icon);
                $(obj).parents("tr").find(".td-status").find('span').toggleClass('layui-btn-disabled').html('已' + args.title);
                layer.msg('已' + args.title + '!', {icon: 1, time: 1000});
            },
            error: function () {
                layer.msg(args.titile + '失败!', {icon: 5, time: 1000});
            }
        });
    });
}

/**
 * 删除行
 * @param obj
 * @param args
 */
function deleteRow(obj, args) {
    layer.confirm('确认要' + args.title + '吗？', function (index) {
        //发异步删除数据
        $.ajax({
            type: args.formType,
            url: args.url,
            timeout: args.timeout,
            success: function () {
                $(obj).parents("tr").remove();
                layer.msg('已' + args.title + '!', {icon: 1, time: 1000});
                $("#dataCount").html(function (index, value) {
                    return parseInt(value) - 1;
                });
            },
            error: function () {
                layer.msg(args.title + '失败!', {icon: 5, time: 1000});
            }
        });
    });
}

/**
 * 批量删除行
 * @param data
 * @param args
 */
function deleteRowAll(data, args) {
    console.log("data:" + data + "length:" + data.length);
    layer.confirm('确认要' + args.title + '吗？(' + data + ')', function (index) {
        //捉到所有被选中的，发异步进行删除
        $.ajax({
            type: args.formType,
            url: args.url,
            timeout: args.timeout,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                layer.msg(args.title + '成功', {icon: 1});
                $("#dataCount").html(function (index, value) {
                    return parseInt(value) - data.length;
                });
                $(".layui-form-checked").not('.header').parents('tr').remove();
            },
            error: function () {
                console.log(JSON.stringify(tableCheck.getData()))
                layer.msg(args.title + '失败!', {icon: 5, time: 1000});
            }
        });
    })
}


function getUrls() {
    var urls = {
        'realPath': $(location).prop('href').toLocaleString(),
        'host': $(location).prop('host').toLocaleString(),
        'path': $(location).prop('pathname').toLocaleString(),
        'protocol': $(location).attr('protocol').toLocaleString(),
        'port': $(location).prop('port').toLocaleString(),
    };
    var path=urls.protocol+"//"+urls.host+urls.path;
    return path;

}
function search_form(data) {
    var _data=delUndefined(data);
    if($.isEmptyObject(_data)){
    $(location).attr("href",getUrls());

    }else{
        $(location).attr("href",getUrls()+"?"+$.param(_data));
    }

}

/**
 * 删除对象中为空的字段
 * @param ob
 * @returns {*}
 */
function delUndefined(ob) {
    for (let e in ob) {
        if (typeof(ob[e]) === 'undefined' || ob[e] === null || ob[e] === '') {
            delete ob[e];
        }
        else if (ob[e].constructor === Object) {
            if (Object.keys(ob[e]).length === 0) {
                delete ob[e];
            } else {
                delUndefined(ob[e]);
            }
        }
        else if (ob[e].constructor === Array) {
            ob[e].map(function (seg) {
                if (typeof(seg) === 'object') {
                    delUndefined(seg);
                }
            });
        }
    }
    return ob;
}

/**
 * 序列化对象
 */
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}
