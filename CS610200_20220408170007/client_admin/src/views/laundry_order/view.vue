<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','clothing_items_') || $check_field('add','clothing_items_') || $check_field('set','clothing_items_')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="衣物名称" prop="clothing_items_">
					<el-input id="clothing_items_" v-model="form['clothing_items_']" placeholder="请输入衣物名称"
							  v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','clothing_items_')) || (!form['laundry_order_id'] && $check_field('add','clothing_items_'))" :disabled="disabledObj['clothing_items__isDisabled']"></el-input>
					<div v-else-if="$check_field('get','clothing_items_')">{{form['clothing_items_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','clothes_type_') || $check_field('add','clothes_type_') || $check_field('set','clothes_type_')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="衣物类型" prop="clothes_type_">
					<el-input id="clothes_type_" v-model="form['clothes_type_']" placeholder="请输入衣物类型"
							  v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','clothes_type_')) || (!form['laundry_order_id'] && $check_field('add','clothes_type_'))" :disabled="disabledObj['clothes_type__isDisabled']"></el-input>
					<div v-else-if="$check_field('get','clothes_type_')">{{form['clothes_type_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','service_price') || $check_field('add','service_price') || $check_field('set','service_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="服务价格" prop="service_price">
					<el-input-number id="service_price" v-model.number="form['service_price']"
						v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','service_price')) || (!form['laundry_order_id'] && $check_field('add','service_price'))"></el-input-number>
					<div v-else-if="$check_field('get','service_price')">{{form['service_price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','user_account') || $check_field('add','user_account') || $check_field('set','user_account')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户账号" prop="user_account">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_user_account(form['user_account']) }}
							<!--<el-input id="business_name" v-model="form['user_account']" placeholder="请输入用户账号"-->
							<!--v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','user_account')) || (!form['laundry_order_id'] && $check_field('add','user_account'))" :disabled="disabledObj['user_account_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','user_account')">{{form['user_account']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','user_account')) || (!form['laundry_order_id'] && $check_field('add','user_account'))" id="user_account" v-model="form['user_account']" :disabled="disabledObj['user_account_isDisabled']">
								<el-option v-for="o in list_user_user_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','user_account')" id="user_account" v-model="form['user_account']" :disabled="true">
								<el-option v-for="o in list_user_user_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="user_account" v-model="form['user_account']" :disabled="disabledObj['user_account_isDisabled']">
							<el-option v-for="o in list_user_user_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','full_name')) || (!form['laundry_order_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="联系电话" prop="contact_number">
					<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" type="tel"
						v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','contact_number')) || (!form['laundry_order_id'] && $check_field('add','contact_number'))">
					</el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','receiving_address') || $check_field('add','receiving_address') || $check_field('set','receiving_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="收取地址" prop="receiving_address">
					<el-input id="receiving_address" v-model="form['receiving_address']" placeholder="请输入收取地址"
							  v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','receiving_address')) || (!form['laundry_order_id'] && $check_field('add','receiving_address'))" :disabled="disabledObj['receiving_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','receiving_address')">{{form['receiving_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','time_of_appointment') || $check_field('add','time_of_appointment') || $check_field('set','time_of_appointment')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="预约时间" prop="time_of_appointment">
					<el-date-picker :disabled="disabledObj['time_of_appointment_isDisabled']" v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','time_of_appointment')) || (!form['laundry_order_id'] && $check_field('add','time_of_appointment'))" id="time_of_appointment"
						v-model="form['time_of_appointment']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','time_of_appointment')">{{form['time_of_appointment']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','order_status') || $check_field('add','order_status') || $check_field('set','order_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单状态" prop="order_status">
					<el-select id="order_status" v-model="form['order_status']"
						v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','order_status')) || (!form['laundry_order_id'] && $check_field('add','order_status'))">
						<el-option v-for="o in list_order_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','order_status')">{{form['order_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单编号" prop="order_number">
					<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单编号"
							  v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','order_number')) || (!form['laundry_order_id'] && $check_field('add','order_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','matters_needing_attention') || $check_field('add','matters_needing_attention') || $check_field('set','matters_needing_attention')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="注意事项" prop="matters_needing_attention">
					<el-input type="textarea" id="matters_needing_attention" v-model="form['matters_needing_attention']" placeholder="请输入注意事项"
						v-if="user_group === '管理员' || (form['laundry_order_id'] && $check_field('set','matters_needing_attention')) || (!form['laundry_order_id'] && $check_field('add','matters_needing_attention'))" :disabled="disabledObj['matters_needing_attention_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','matters_needing_attention')">{{form['matters_needing_attention']}}</div>
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
				field: "laundry_order_id",
				url_add: "~/api/laundry_order/add?",
				url_set: "~/api/laundry_order/set?",
				url_get_obj: "~/api/laundry_order/get_obj?",
				url_upload: "~/api/laundry_order/upload?",

				query: {
					"laundry_order_id": 0,
				},

				form: {
					"clothing_items_":'', // 衣物名称
					"clothes_type_":'', // 衣物类型
					"service_price":0, // 服务价格
					"user_account": 0, // 用户账号
					"full_name":'', // 姓名
					"contact_number":'', // 联系电话
					"receiving_address":'', // 收取地址
					"time_of_appointment":'', // 预约时间
					"order_status":'', // 订单状态
					"order_number":this.$get_stamp(), // 订单编号
					"matters_needing_attention":'', // 注意事项
					"laundry_order_id": 0, // ID

				},
				disabledObj:{
					"clothing_items__isDisabled": false,
					"clothes_type__isDisabled": false,
					"user_account_isDisabled": false,
					"full_name_isDisabled": false,
					"contact_number_isDisabled": false,
					"receiving_address_isDisabled": false,
					"time_of_appointment_isDisabled": false,
					"order_status_isDisabled": false,
					"order_number_isDisabled": false,
					"matters_needing_attention_isDisabled": false,
				},
				// 用户列表
				list_user_user_account: [],
				// 用户组
				group_user_user_account: "",
				//订单状态选项列表
				list_order_status: ['已取消','待取衣','已取衣','洗衣中','待送衣','送衣中','已送达'],
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
			/**
			 * 获取注册用户用户组
			 */
			async get_group_user_user_account() {
				this.form["user_account"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=注册用户");
				if(json.result && json.result.obj){
					this.group_user_user_account = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_user_account(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_user_account.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="user_account") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
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
        if (this.form["time_of_appointment"].indexOf("-")===-1){
          this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["time_of_appointment"]) > 9999){
					this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/laundry_order/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/laundry_order/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/laundry_order/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/laundry_order/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/laundry_order/view','get');
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
			this.get_group_user_user_account();
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
