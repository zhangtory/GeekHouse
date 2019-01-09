<@override name="mainpage">

    <div class="container">

        <div class="page-header">
            <h2>卧室灯</h2>
        </div>
        <div>
            <button type="button" class="btn_control btn btn-lg btn-success" onclick="light_ctl('ON')">开灯</button>
            <button type="button" class="btn_control btn btn-lg btn-danger" onclick="light_ctl('OFF')">关灯</button>
        </div>

    </div><!-- /.container -->

</@override>
<@extends name="base.ftl"/>
