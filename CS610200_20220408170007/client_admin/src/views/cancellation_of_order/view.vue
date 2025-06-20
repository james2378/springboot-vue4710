<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单编号" prop="order_number">
					<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单编号"
							  v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','order_number')) || (!form['cancellation_of_order_id'] && $check_field('add','order_number'))" :disabled="disabledObj['order_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','clothing_items_') || $check_field('add','clothing_items_') || $check_field('set','clothing_items_')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="衣物名称" prop="clothing_items_">
					<el-input id="clothing_items_" v-model="form['clothing_items_']" placeholder="请输入衣物名称"
							  v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','clothing_items_')) || (!form['cancellation_of_order_id'] && $check_field('add','clothing_items_'))" :disabled="disabledObj['clothing_items__isDisabled']"></el-input>
					<div v-else-if="$check_field('get','clothing_items_')">{{form['clothing_items_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','clothes_type_') || $check_field('add','clothes_type_') || $check_field('set','clothes_type_')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="衣物类型" prop="clothes_type_">
					<el-input id="clothes_type_" v-model="form['clothes_type_']" placeholder="请输入衣物类型"
							  v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','clothes_type_')) || (!form['cancellation_of_order_id'] && $check_field('add','clothes_type_'))" :disabled="disabledObj['clothes_type__isDisabled']"></el-input>
					<div v-else-if="$check_field('get','clothes_type_')">{{form['clothes_type_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','user_account') || $check_field('add','user_account') || $check_field('set','user_account')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户账号" prop="user_account">
						<el-select v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','user_account')) || (!form['cancellation_of_order_id'] && $check_field('add','user_account'))" id="user_account" v-model="form['user_account']" :disabled="disabledObj['user_account_isDisabled']">
							<el-option v-for="o in list_user_user_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','user_account')" id="user_account" v-model="form['user_account']" :disabled="true">
							<el-option v-for="o in list_user_user_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','full_name')) || (!form['cancellation_of_order_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','audit_status') || $check_field('add','audit_status') || $check_field('set','audit_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核状态" prop="audit_status">
					<el-select id="audit_status" v-model="form['audit_status']"
						v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','audit_status')) || (!form['cancellation_of_order_id'] && $check_field('add','audit_status'))">
						<el-option v-for="o in list_audit_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','audit_status')">{{form['audit_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cancellation_reason') || $check_field('add','cancellation_reason') || $check_field('set','cancellation_reason')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="取消原因" prop="cancellation_reason">
					<el-input type="textarea" id="cancellation_reason" v-model="form['cancellation_reason']" placeholder="请输入取消原因"
						v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','cancellation_reason')) || (!form['cancellation_of_order_id'] && $check_field('add','cancellation_reason'))" :disabled="disabledObj['cancellation_reason_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','cancellation_reason')">{{form['cancellation_reason']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','review_reply') || $check_field('add','review_reply') || $check_field('set','review_reply')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核回复" prop="review_reply">
					<el-input type="textarea" id="review_reply" v-model="form['review_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','review_reply')) || (!form['cancellation_of_order_id'] && $check_field('add','review_reply'))" :disabled="disabledObj['review_reply_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','review_reply')">{{form['review_reply']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "cancellation_of_order_id",
				url_add: "~/api/cancellation_of_order/add?",
				url_set: "~/api/cancellation_of_order/set?",
				url_get_obj: "~/api/cancellation_of_order/get_obj?",
				url_upload: "~/api/cancellation_of_order/upload?",

				query: {
					"cancellation_of_order_id": 0,
				},

				form: {
					"order_number":'', // 订单编号
					"clothing_items_":'', // 衣物名称
					"clothes_type_":'', // 衣物类型
					"user_account": 0, // 用户账号
					"full_name":'', // 姓名
					"audit_status":'', // 审核状态
					"cancellation_reason":'', // 取消原因
					"review_reply":'', // 审核回复
					"cancellation_of_order_id": 0, // ID

				},
				disabledObj:{
					"order_number_isDisabled": false,
					"clothing_items__isDisabled": false,
					"clothes_type__isDisabled": false,
					"user_account_isDisabled": false,
					"full_name_isDisabled": false,
					"audit_status_isDisabled": false,
					"cancellation_reason_isDisabled": false,
					"review_reply_isDisabled": false,
				},
				// 用户列表
				list_user_user_account: [],
				//审核状态选项列表
				list_audit_status: ['通过','不通过'],
			}
		},
		methods: {
			/**
			 * 获取注册用户用户列表
			 */
			async get_list_user_user_account() {
                // if(this.user_group !== "管理员" && this.form["user_account"] === 0) {
                //     this.form["user_account"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=注册用户");
                if(json.result && json.result.list){
                    this.list_user_user_account = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_user_account(id){
				var obj = this.list_user_user_account.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "用户账号":
							if(param["user_account"] > 0){
								param["user_account"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/cancellation_of_order/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/cancellation_of_order/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/cancellation_of_order/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/cancellation_of_order/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/cancellation_of_order/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_user_account();
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
