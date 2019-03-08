<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>文件列表页</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>

<body>

<section id="container">
    <header class="header black-bg">
        <div class="sidebar-toggle-box">
            <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
        </div>

        <a class="logo"><b>文件管理系统</b></a>

        <div class="top-menu">
            <ul class="nav pull-right top-menu">
                <li><a class="logout" href="login.html">Logout</a></li>
                <li>
                    <button class="logout" id="savedatebase">备份数据库</button>
                </li>
            </ul>
        </div>
    </header>

    <aside>
        <div id="sidebar" class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu" id="nav-accordion">

                <p class="centered"><a href="profile.html"><img src="assets/img/ui-sam.jpg" class="img-circle"
                                                                width="60"></a></p>

                <h5 class="centered">


                    <s:if test="%{#session.user!=null}">

                        <s:property value="#session.user.nickname"/>

                    </s:if>
                    <s:if test="%{#session.user==null}">
                        您好！<a href="login.html">请登录</a>
                    </s:if>
                </h5>
                <li class="mt">
                    <a class="active" href="file.jsp">
                        <i class="fa fa-desktop"></i>
                        <span>文件管理</span>
                    </a>
                </li>
                <s:if test="%{#session.user!=null}">
                    <s:if test="%{#session.user.permission==1}">
                        <li class="mt">
                            <a href="users.jsp">
                                <i class="fa fa-tasks"></i>
                                <span>用户管理</span>
                            </a>
                        </li>

                        <li class="mt">
                            <a href="operation_record.jsp">
                                <i class="fa fa-book"></i>
                                <span>操作记录</span>
                            </a>
                        </li>
                    </s:if>
                </s:if>


            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--sidebar end-->

    <!-- **********************************************************************************************************************************************************
    MAIN CONTENT
    *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <div class="row mt">
                <div class="col-md-12">
                    <div class="content-panel">
                        <font size="4"><i class="fa fa-angle-right"></i> 文件列表</font>
                        <s:if test="%{#session.user!=null}">
                            <s:if test="%{#session.user.permission<=2}">
                                <span style="float: right;padding-right: 5%">
                                    <li class="btn btn-primary" data-toggle="modal" data-target="#myModal">上传文件</li>
                                </span>
                            </s:if>
                        </s:if>
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">请选择要上传的文件</h4>
                                    </div>
                                    <div class="modal-body">
                                        文件路径<br>
                                        <input id='location' class="form-control" disabled>
                                        <form action="file_upload.action" enctype="multipart/form-data" method="post">
                                            文件描述：<br>
                                            <input name="description" class="form-control">

                                            <input type="file" onchange="$('#location').val($('#file1').val());"
                                                   name="file1" id="file1" style="display: none"><br/>
                                            <input class="btn btn-theme02" id="submit" type="submit" value="提交"
                                                   style="display: none">
                                        </form>
                                    </div>
                                    <div class="modal-footer">

                                        <button type="button" class="btn btn-theme03" onclick="$('#file1').click()">
                                            浏览文件
                                        </button>
                                        <button type="button" class="btn btn-primary"
                                                onclick="$('#submit').click()">
                                            上传数据
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        <hr>
                        <table id="FileTable">
                        </table>
                    </div><!-- /content-panel -->
                </div><!-- /col-md-12 -->
            </div><!-- /row -->

        </section>
        <! --/wrapper -->
    </section><!-- /MAIN CONTENT -->


</section>



<!-- js placed at the end of the document so the pages load faster -->
<script src="assets/js/jquery.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="assets/js/jquery.scrollTo.min.js"></script>
<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>


<!--common script for all pages-->
<script src="assets/js/common-scripts.js"></script>
<script src="assets/js/bootstrap-table.js"></script>
<link href="assets/css/bootstrap-table.css">


<script>

    $(function () {
        <s:if test="%{#session.user!=null}">

        $("#FileTable").bootstrapTable({
            url: 'file_list',         //请求后台的URL（*）
            method: 'post',                      //请求方式（*）
            //toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            //queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            //height: 800,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [

                {
                    field: 'id',
                    title: 'ID'
                },
                {
                    field: 'filename',
                    title: '文件名'
                },
                {
                    field: 'description',
                    title: '描述'
                },
                {
                    field: 'uploader',
                    title: '上传者'
                },
                {

                    title: '状态',
                    formatter: function () {
                        return "<span class=\"label label-warning label-mini\">可用</span>"
                    }
                },
                {
                    title: '操作',
                    formatter: function (value, row, index) {
                        var permission = -1;
                        var bdownload = "<button name=\"download\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-download\"></i></button>";
                        var bdelete = "<button name=\"delete\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o \"></i></button>";

                        permission =${user.permission};
                        if (permission == 1) {
                            return bdownload + bdelete;
                        } else {
                            return bdownload;
                        }
                    }
                }]
        });

        </s:if>
        $(document).on('click', 'button[name="delete"]', function () {
            var fileID = $(this).parent().parent().children("td:eq(0)").text();
            var fileName = $(this).parent().parent().children("td:eq(1)").text();
            if (confirm("确认删除" + fileName)) {
                //alert("执行删除")
                var jsonT = "[";

                jsonT += '{"id":"' + fileID + '","name":"' + fileName + '"},'

                jsonT = jsonT.substr(0, jsonT.length - 1);
                jsonT += "]";
                $.ajax({
                    type: 'post',
                    url: 'file_delete.action',
                    data: {file: jsonT},
                    success: function (msg) {
                        alert(msg);
                        window.location.href = 'file.jsp';
                    }
                });

            }
        });
        $(document).on('click', 'button[name="download"]', function () {
            var fileID = $(this).parent().parent().children("td:eq(0)").text();
            var fileName = $(this).parent().parent().children("td:eq(1)").text();
            //alert(fileID + fileName)

            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "get");
            form.attr("action", "file_download");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "id");
            input1.attr("value", fileID);
            var input2 = $("<input>");
            input2.attr("type", "hidden");
            input2.attr("name", "name");
            input2.attr("value", fileName);

            $("body").append(form);
            form.append(input1);
            form.append(input2);
            form.submit();
            form.remove();

        });

    });
    $("#savedatebase").click(function () {
        $.ajax({
            type: "POST",
            url: "SaveServlet",
            success: function (msg) {
                alert(msg);
            },
            error: function () {
                alert("失败");
            }
        });
    });


</script>

</body>
</html>

