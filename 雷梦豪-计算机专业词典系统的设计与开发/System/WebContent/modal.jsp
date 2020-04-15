<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="modal fade" id="changePassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="password" class="modify" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
            	<div class="form-group form-inline">
					<label for="abbreviation">输入原密码：</label>
					<input type="password" class="form-control" id="oldPwd" style="width: 60%;">
				</div>
				<div class="form-group form-inline">
					<label for="fullWords">输入新密码：</label>
					<input type="password" class="form-control" id="newPwd" style="width: 60%;">
				</div>	
				
				<div class="form-group form-inline">
					<label for="specificMeanning" >验证新密码：</label>
					<input type="password" class="form-control" id="pwd" style="width: 60%;">
					<em id="different" style="color:red;display:none;">*新密码输入不一致</em>
				</div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="changePwdCancel">取消</button>
                <button type="button" class="btn btn-primary" id="changePwd">确定</button>
            </div>
        </div>
    </div>
</div>