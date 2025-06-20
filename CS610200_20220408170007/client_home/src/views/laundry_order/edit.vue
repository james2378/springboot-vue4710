<template>
	<div class="diy_edit page_laundry_order" id="laundry_order_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','clothing_items_') || $check_field('add','clothing_items_') || $check_field('get','clothing_items_')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>衣物名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_clothing_items_" v-model="form['clothing_items_']" placeholder="请输入衣物名称" v-if="(form['clothing_items_'] && $check_field('set','clothing_items_')) || (!form['clothing_items_'] && $check_field('add','clothing_items_'))"  :disabled="disabledObj['clothing_items__isDisabled']"/>
							<span v-else-if="$check_field('get','clothing_items_')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','clothes_type_') || $check_field('add','clothes_type_') || $check_field('get','clothes_type_')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>衣物类型:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_clothes_type_" v-model="form['clothes_type_']" placeholder="请输入衣物类型" v-if="(form['clothes_type_'] && $check_field('set','clothes_type_')) || (!form['clothes_type_'] && $check_field('add','clothes_type_'))"  :disabled="disabledObj['clothes_type__isDisabled']"/>
							<span v-else-if="$check_field('get','clothes_type_')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','service_price') || $check_field('add','service_price') || $check_field('get','service_price')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>服务价格:
							</span>
						</div>
						<!-- 数字 -->
						<div class="diy_field diy_number">
							<input type="number" id="form_service_price" v-model.number="form['service_price']" placeholder="请输入服务价格" v-if="(form['service_price'] && $check_field('set','service_price')) || (!form['service_price'] && $check_field('add','service_price'))" :disabled="disabledObj['service_price_isDisabled']" />
							<span v-else-if="$check_field('get','service_price')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','user_account') || $check_field('add','user_account') || $check_field('get','user_account')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>用户账号:
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
					<div v-if="$check_field('set','contact_number') || $check_field('add','contact_number') || $check_field('get','contact_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>联系电话:
							</span>
						</div>
						<!-- 手机 -->
						<div class="diy_field diy_phone">
							<input type="tel" id="form_contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" v-if="(form['contact_number'] && $check_field('set','contact_number')) || (!form['contact_number'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']" />
							<span v-else-if="$check_field('get','contact_number')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','receiving_address') || $check_field('add','receiving_address') || $check_field('get','receiving_address')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>收取地址:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_receiving_address" v-model="form['receiving_address']" placeholder="请输入收取地址" v-if="(form['receiving_address'] && $check_field('set','receiving_address')) || (!form['receiving_address'] && $check_field('add','receiving_address'))"  :disabled="disabledObj['receiving_address_isDisabled']"/>
							<span v-else-if="$check_field('get','receiving_address')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','time_of_appointment') || $check_field('add','time_of_appointment') || $check_field('get','time_of_appointment')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>预约时间:
							</span>
						</div>
						<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['time_of_appointment_isDisabled']" id="form_time_of_appointment" v-model="form['time_of_appointment']" placeholder="请输入预约时间" v-if="(form['time_of_appointment'] && $check_field('set','time_of_appointment')) || (!form['time_of_appointment'] && $check_field('add','time_of_appointment'))" />
							<span v-else-if="$check_field('get','time_of_appointment')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','order_status') || $check_field('add','order_status') || $check_field('get','order_status')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>订单状态:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_order_status" v-model="form['order_status']" v-if="(form['order_status'] && $check_field('set','order_status')) || (!form['order_status'] && $check_field('add','order_status'))" >
								<option v-for="o in list_order_status" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','order_status')">{{ form['order_status'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','order_number') || $check_field('add','order_number') || $check_field('get','order_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>订单编号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_order_number" v-model="form['order_number']" placeholder="请输入订单编号" v-if="(form['order_number'] && $check_field('set','order_number')) || (!form['order_number'] && $check_field('add','order_number'))" :disabled="true"/>
							<span v-else-if="$check_field('get','order_number')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','matters_needing_attention') || $check_field('add','matters_needing_attention') || $check_field('get','matters_needing_attention')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>注意事项:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_matters_needing_attention" v-model="form['matters_needing_attention']" v-if="(form['matters_needing_attention'] && $check_field('set','matters_needing_attention')) || (!form['matters_needing_attention'] && $check_field('add','matters_needing_attention'))" :disabled="disabledObj['matters_needing_attention_isDisabled']" />
							<span v-else-if="$check_field('get','matters_needing_attention')">{{ form['full_name'] }}</span>
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
				url_get_obj: "~/api/laundry_order/get_obj?",
				url_add: "~/api/laundry_order/add?",
				url_set: "~/api/laundry_order/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"clothing_items_": "",
					"clothes_type_": "",
					"service_price": 0,
					"user_account": 0,
					"full_name": "",
					"contact_number": "",
					"receiving_address": "",
					"time_of_appointment": "",
					"order_status": "",
					"order_number": "",
					"matters_needing_attention": "",
					"laundry_order_id": 0,
				},

				obj: {
					"clothing_items_":'', // 衣物名称
					"clothes_type_":'', // 衣物类型
					"service_price":0, // 服务价格
					"user_account": 0, // 用户账号
					"full_name":'', // 姓名
					"contact_number":'', // 联系电话
					"receiving_address":'', // 收取地址
					"time_of_appointment": "1970-01-01 00:00:00",
					"order_status":'', // 订单状态
					"order_number":this.$get_stamp(), // 订单编号
					"matters_needing_attention":'', // 注意事项
					"laundry_order_id": 0,
				},

				// 表单字段
				form: {
					"clothing_items_":'', // 衣物名称
					"clothes_type_":'', // 衣物类型
					"service_price":0, // 服务价格
					"user_account": 0, // 用户账号
					"full_name":'', // 姓名
					"contact_number":'', // 联系电话
					"receiving_address":'', // 收取地址
					"time_of_appointment": "1970-01-01 00:00:00",
					"order_status":'', // 订单状态
					"order_number":this.$get_stamp(), // 订单编号
					"matters_needing_attention":'', // 注意事项
					"laundry_order_id": 0,
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
				//订单状态选项列表
				list_order_status: ['已取消','待取衣','已取衣','洗衣中','待送衣','送衣中','已送达'],

				// ID字段
				field: "laundry_order_id",
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
				this.$post("~/api/laundry_order/upload?", form, (res) => {
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
				this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd")
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
