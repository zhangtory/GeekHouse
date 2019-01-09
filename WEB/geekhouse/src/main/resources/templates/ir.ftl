<@override name="mainpage">
    <div class="container">
        <div class="page-header">
            <h2>空调</h2>
        </div>
        <div>
            <button type="button" class="btn_control btn btn-lg btn-success" onclick="ir_ctl('ON')">打开</button>
            <button type="button" class="btn_control btn btn-lg btn-danger" onclick="ir_ctl('OFF')">关闭</button>
        </div>

    </div><!-- /.container -->
</@override>
<@extends name="base.ftl"/>
