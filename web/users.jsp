<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">

<head>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>用户管理</title>

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

    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>
    <script>

        $(document).ready(function () {
            <s:if test="%{#session.user!=null}">
            $("#user_table").bootstrapTable({
                url: 'ShowUserServlet',         //请求后台的URL（*）
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
                        field: 'account',
                        title: '账号'
                    }, {
                        field: 'nickname',
                        title: '昵称'
                    },
                    {
                        field: 'permission',
                        title: '权限'
                    },
                    {
                        title: '操作',
                        formatter: function () {
                            var bt = "<button name=\"delete\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-trash-o\">删除</i></button>";
                            return bt;
                        }
                    }]
            });

            </s:if>

        });


        $(document).on('click', 'button[name="delete"]', function () {
            var id = $(this).parent().parent().children("td:eq(0)").text();
            if (confirm("确认删除" + id)) {
                //alert("执行删除");
                $.ajax({
                    type: 'post',
                    url: 'DeleteUserServlet',
                    data: {id: id},
                    success: function (msg) {
                        alert(msg);
                        window.location.href = 'users.jsp';
                    }
                });

            }
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
                        ${user.nickname}
                    </s:if>
                    <s:if test="%{#session.user==null}">
                        您好！<a href="login.html">请登录</a>
                    </s:if>
                </h5>
                <li class="mt">
                    <a href="file.jsp">
                        <i class="fa fa-desktop"></i>
                        <span>文件管理</span>
                    </a>
                </li>
                <s:if test="%{#session.user!=null}">
                    <s:if test="%{#session.user.permission==1}">
                        <li class="mt">
                            <a class="active" href="users.jsp">
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
    <section id="main-content">
        <section class="wrapper">
            <div class="row mt">
                <div class="col-md-12">
                    <div class="content-panel">
                        <font size="4"><i class="fa fa-angle-right">用户数据表</i></font>
                        <span style="float: right;padding-right: 5%">
                            <li class="btn btn-primary" data-toggle="modal" data-target="#myModal"><i
                                    class="fa fa-plus"></i> 添加用户
                        </li>
                        </span>
                        <hr>
                        <table id="user_table">

                        </table>

                    </div>
                </div>
            </div>
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                 aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel">添加用户</h4>
                        </div>
                        <div class="modal-body">
                            <form method="post" action="InsertUserServlet">
                                <label>账号</label>
                                <input type="text" name="account" class="form-control">
                                <label>昵称</label>
                                <input type="text" name="nickname" class="form-control">
                                <label>密码</label>
                                <input type="password" name="password" class="form-control">
                                <label>权限</label>
                                <select name="permission" class="form-control">
                                    <option selected="selected" value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>

                                </select>
                                <input type="submit" value="确认添加" class="btn">

                                <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            </form>


                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->

            </div>

        </section>

    </section>


</section>


</body>

</html>