<@override name="mainpage">
    <div class="container">
        <div class="page-header">
            <h2>空调</h2>
        </div>

        <div>
            <form action="/ir/addPlan" method="post">
                <ul class="addplan_ul">
                    <li>
                        <span>执行时间：</span>
                        <select name="hour">
                        <#list 0..23 as h>
                            <option value="${h}">${h}</option>
                        </#list>
                        </select>
                        <span>时</span>
                        <select name="minute">
                        <#list 0..59 as m>
                            <option value="${m}">${m}</option>
                        </#list>
                        </select>
                        <span>分</span>
                    </li>
                    <li>
                        <span>操作选择：</span>
                        <select name="option">
                        <#list oplist as op>
                            <option value="${op['value']}">${op['name']}</option>
                        </#list>
                        </select>
                    </li>
                </ul>

                <button type="submit" class="btn btn-lg btn-success">保存</button>
            </form>
        </div>

    </div><!-- /.container -->

</@override>
<@extends name="base.ftl"/>
