<template>
	<div class="diy_edit page_application_postman" id="application_postman_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','user_account') || $check_field('add','user_account') || $check_field('get','user_account')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>用户账户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_user_account" :disabled="disabledObj['user_account_isDisabled']" v-model="form['user_account']" v-if="(form['user_account'] && $check_field('set','user_account')) || (!form['user_account'] && $check_field('add','user_account'))" >
								<option v-for="o in list_user_user_account" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','user_account')">{{ form['user_account'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','full_name') || $check_field('add','full_name') || $check_field('get','full_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_full_name" v-model="form['full_name']" placeholder="请输入姓名" v-if="(form['full_name'] && $check_field('set','full_name')) || (!form['full_name'] && $check_field('add','full_name'))"  :disabled="disabledObj['full_name_isDisabled']"/>
							<span v-else-if="$check_field('get','full_name')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','id_number') || $check_field('add','id_number') || $check_field('get','id_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>身份证号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_id_number" v-model="form['id_number']" placeholder="请输入身份证号" v-if="(form['id_number'] && $check_field('set','id_number')) || (!form['id_number'] && $check_field('add','id_number'))"  :disabled="disabledObj['id_number_isDisabled']"/>
							<span v-else-if="$check_field('get','id_number')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','contact_number') || $check_field('add','contact_number') || $check_field('get','contact_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>联系电话:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" v-if="(form['contact_number'] && $check_field('set','contact_number')) || (!form['contact_number'] && $check_field('add','contact_number'))"  :disabled="disabledObj['contact_number_isDisabled']"/>
							<span v-else-if="$check_field('get','contact_number')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','audit_status') || $check_field('add','audit_status') || $check_field('get','audit_status')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>审核状态:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_audit_status" v-model="form['audit_status']" v-if="(form['audit_status'] && $check_field('set','audit_status')) || (!form['audit_status'] && $check_field('add','audit_status'))" >
								<option v-for="o in list_audit_status" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','audit_status')">{{ form['audit_status'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','review_reply') || $check_field('add','review_reply') || $check_field('get','review_reply')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>审核回复:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_review_reply" v-model="form['review_reply']" placeholder="请输入审核回复" v-if="(form['review_reply'] && $check_field('set','review_reply')) || (!form['review_reply'] && $check_field('add','review_reply'))"  :disabled="disabledObj['review_reply_isDisabled']"/>
							<span v-else-if="$check_field('get','review_reply')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','reasons_for_application') || $check_field('add','reasons_for_application') || $check_field('get','reasons_for_application')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>申请理由:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_reasons_for_application" v-model="form['reasons_for_application']" v-if="(form['reasons_for_application'] && $check_field('set','reasons_for_application')) || (!form['reasons_for_application'] && $check_field('add','reasons_for_application'))" :disabled="disabledObj['reasons_for_application_isDisabled']" />
							<span v-else-if="$check_field('get','reasons_for_application')">{{ form['full_name'] }}</span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/application_postman/get_obj?",
				url_add: "~/api/application_postman/add?",
				url_set: "~/api/application_postman/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"user_account": 0,
					"full_name": "",
					"id_number": "",
					"contact_number": "",
					"audit_status": "",
					"review_reply": "",
					"reasons_for_application": "",
					"application_postman_id": 0,
				},

				obj: {
					"user_account": 0, // 用户账户
					"full_name":'', // 姓名
					"id_number":'', // 身份证号
					"contact_number":'', // 联系电话
					"audit_status":'', // 审核状态
					"review_reply":'', // 审核回复
					"reasons_for_application":'', // 申请理由
					"application_postman_id": 0,
				},

				// 表单字段
				form: {
					"user_account": 0, // 用户账户
					"full_name":'', // 姓名
					"id_number":'', // 身份证号
					"contact_number":'', // 联系电话
					"audit_status":'', // 审核状态
					"review_reply":'', // 审核回复
					"reasons_for_application":'', // 申请理由
					"application_postman_id": 0,
				},
				disabledObj:{
					"user_account_isDisabled": false,
					"full_name_isDisabled": false,
					"id_number_isDisabled": false,
					"contact_number_isDisabled": false,
					"audit_status_isDisabled": false,
					"review_reply_isDisabled": false,
					"reasons_for_application_isDisabled": false,
				},
				// 用户列表
				list_user_user_account: [],
				//审核状态选项列表
				list_audit_status: ['通过','不通过'],

				// ID字段
				field: "application_postman_id",
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
			async get_user_session_user_account(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=注册用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["user_account"] = user_id
								_this.disabledObj['user_account' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="user_account") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/application_postman/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_user_account();
			this.get_list_user_user_account();
		},
	}
</script>

<style>
</style>
