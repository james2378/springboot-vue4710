<template>
	<el-main class="bg table_wrap">
		<el-form label-position="right" :model="query" class="form p_4" label-width="120">
			<el-row>
				<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="衣物名称">
						<el-input v-model="query.clothing_items_"></el-input>
					</el-form-item>
				</el-col>
				<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="姓名">
						<el-input v-model="query.full_name"></el-input>
					</el-form-item>
				</el-col>
				<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="订单状态">
						<el-select v-model="query.order_status">
                            <el-option v-for="o in list_order_status" :key="o" :label="o"
                            	:value="o">
                            </el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :xs="24" :sm="10" :lg="8" class="search_btn_wrap_1">
					<el-form-item>
						<el-button type="primary" @click="search()" class="search_btn_find">查询</el-button>
						<el-button @click="reset()" style="margin-right: 74px;" class="search_btn_reset">重置</el-button>
						<router-link v-if="user_group == '管理员' || $check_action('/laundry_order/table','add') || $check_action('/laundry_order/view','add')" class="el-button el-button--default el-button--primary search_btn_add" to="./view?">添加
						</router-link>
            <el-button v-if="user_group == '管理员' || $check_action('/laundry_order/table','del') || $check_action('/laundry_order/view','del')" class="search_btn_del" type="danger" @click="delInfo()">删除</el-button>
					</el-form-item>
				</el-col>

			</el-row>
		</el-form>
		<el-table :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%" id="dataTable">
			<el-table-column fixed type="selection" tooltip-effect="dark" width="55">
			</el-table-column>
			<el-table-column prop="clothing_items_" label="衣物名称"
				v-if="user_group == '管理员' || $check_field('get','clothing_items_')" min-width="200">
			</el-table-column>
			<el-table-column prop="clothes_type_" label="衣物类型"
				v-if="user_group == '管理员' || $check_field('get','clothes_type_')" min-width="200">
			</el-table-column>
			<el-table-column prop="service_price" label="服务价格"
				v-if="user_group == '管理员' || $check_field('get','service_price')" min-width="200">
			</el-table-column>
			<el-table-column prop="user_account" label="用户账号"
				v-if="user_group == '管理员' || $check_field('get','user_account')" min-width="200">
				<template slot-scope="scope">
					{{ get_user_user_account(scope.row['user_account']) }}
				</template>
			</el-table-column>
			<el-table-column prop="full_name" label="姓名"
				v-if="user_group == '管理员' || $check_field('get','full_name')" min-width="200">
			</el-table-column>
			<el-table-column prop="contact_number" label="联系电话"
				v-if="user_group == '管理员' || $check_field('get','contact_number')" min-width="200">
			</el-table-column>
			<el-table-column prop="receiving_address" label="收取地址"
				v-if="user_group == '管理员' || $check_field('get','receiving_address')" min-width="200">
			</el-table-column>
			<el-table-column prop="time_of_appointment" label="预约时间"
				v-if="user_group == '管理员' || $check_field('get','time_of_appointment')" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["time_of_appointment"],"yyyy-MM-dd") }}
                </template>
			</el-table-column>
			<el-table-column prop="order_status" label="订单状态"
				v-if="user_group == '管理员' || $check_field('get','order_status')" min-width="200">
			</el-table-column>
			<el-table-column prop="order_number" label="订单编号"
				v-if="user_group == '管理员' || $check_field('get','order_number')" min-width="200">
			</el-table-column>
			<el-table-column prop="matters_needing_attention" label="注意事项"
				v-if="user_group == '管理员' || $check_field('get','matters_needing_attention')" min-width="200">
			</el-table-column>
			<el-table-column label="支付状态" prop="pay_state" v-if="user_group == '管理员' || $check_field('get','pay_state')">
			</el-table-column>

			<el-table-column label="支付类型" prop="pay_type" v-if="user_group == '管理员' || $check_field('get','pay_type')">
			</el-table-column>

            <el-table-column sortable prop="create_time" label="创建时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["create_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
            </el-table-column>

			<el-table-column sortable prop="update_time" label="更新时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["update_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
			</el-table-column>
			<el-table-column fixed="right" label="操作" min-width="120" v-if="user_group == '管理员' || $check_action('/laundry_order/table','set') || $check_action('/laundry_order/view','set') || $check_action('/laundry_order/view','get') || $check_action('/取消订单|安排收送/table','add') || $check_action('/取消订单|安排收送/view','add')" >


				<template slot-scope="scope">
					<router-link class="el-button el-button--small is-plain el-button--success" style="margin: 5px !important;"
					v-if="user_group == '管理员' || $check_action('/laundry_order/table','set') || $check_action('/laundry_order/view','set') || $check_action('/laundry_order/view','get')"
						:to="'./view?' + field + '=' + scope.row[field]"
						 size="small">
						<span>详情</span>
					</router-link>
					<!--跨表按钮-->
					<el-button class="el-button el-button--small is-plain el-button--default" style="margin: 5px !important;" size="small" @click="to_table(scope.row,'/cancellation_of_order/view')" v-if="user_group == '管理员' || $check_action('/cancellation_of_order/table','add') || $check_action('/cancellation_of_order/view','add')">
						<span>取消订单</span>
					</el-button>
					<el-button class="el-button el-button--small is-plain el-button--default" style="margin: 5px !important;" size="small" @click="to_table(scope.row,'/delivery_record/view')" v-if="user_group == '管理员' || $check_action('/delivery_record/table','add') || $check_action('/delivery_record/view','add')">
						<span>安排收送</span>
					</el-button>
					<el-button class="el-button el-button--small is-plain el-button--default" style="margin: 5px !important;" size="small" @click="openPayModal(scope.row)" v-if="scope.row.pay_state==='未支付' && (user_group == '管理员' || $check_pay('/laundry_order/table'))">
						<span>支付</span>
					</el-button>
				</template>
			</el-table-column>

		</el-table>

		<!-- 分页器 -->
		<div class="mt text_center">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
				:current-page="query.page" :page-sizes="[7, 10, 30, 100]" :page-size="query.size"
				layout="total, sizes, prev, pager, next, jumper" :total="count">
			</el-pagination>
		</div>
		<!-- /分页器 -->
		<el-dialog
				title="支付"
				:visible.sync="payModalVisible"
				width="500px"
		>
			<el-tabs v-model="pay_obj.payActiveName" :stretch="true">
				<el-tab-pane label="微信" name="微信">
					<div style="width: 80%;margin: 0 auto;">
						<div style="width: 60%;margin: 30px auto;">
							<el-image style="width: 100%;" :src="$fullUrl('/api/upload/wx.png')"
									  :preview-src-list="[$fullUrl('/api/upload/wx.png')]">
								<div slot="error" class="image-slot">
									<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
								</div>
							</el-image>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<div style="width: 100%;background-color: #409EFF;color: white;font-size: 14px;text-align: center;line-height: 40px;cursor: pointer" @click="submitPay()">确认支付</div>
						</div>
					</div>
				</el-tab-pane>
				<el-tab-pane label="支付宝" name="支付宝">
					<div style="width: 80%;margin: 0 auto;">
						<div style="width: 60%;margin: 30px auto;">
							<el-image style="width: 100%;" :src="$fullUrl('/api/upload/aplipay.png')"
									  :preview-src-list="[$fullUrl('/api/upload/aplipay.png')]">
								<div slot="error" class="image-slot">
									<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
								</div>
							</el-image>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<div style="width: 100%;background-color: #409EFF;color: white;font-size: 14px;text-align: center;line-height: 40px;cursor: pointer" @click="submitPay()">确认支付</div>
						</div>
					</div>
				</el-tab-pane>
				<el-tab-pane label="网银" name="网银">
					<div style="width: 80%;margin: 0 auto;margin-top: 30px;">
						<div style="width: 80%;margin: 0 auto;">
							<div style="line-height: 40px">请输入网银账号：</div>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<el-input v-model="pay_obj.account" placeholder="请输入网银账号"></el-input>
						</div>
					</div>
					<div style="width: 80%;margin: 0 auto;margin-top: 20px;">
						<div style="width: 80%;margin: 0 auto;">
							<div style="line-height: 40px">请输入支付密码，6位数字：</div>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<el-input placeholder="请输入密码" v-model="pay_obj.password" show-password maxlength="6"></el-input>
						</div>
					</div>
					<div style="width: 80%;margin: 0 auto;margin-top: 40px;">
						<div style="width: 100%;background-color: #409EFF;color: white;font-size: 14px;text-align: center;line-height: 40px;cursor: pointer" @click="submitPay()">确认支付</div>
					</div>
				</el-tab-pane>
			</el-tabs>
		</el-dialog>

		<div class="modal_wrap" v-if="showModal">
			<div class="modal_box">
				<!-- <div class="modal_box_close" @click="closeModal">X</div> -->
				<p class="modal_box_title">重要提醒</p>
				<p class="modal_box_text">当前有数据达到预警值！</p>
				<div class="btn_box">
					<span @click="closeModal">取消</span>
					<span @click="closeModal">确定</span>
				</div>
			</div>
		</div>


	</el-main>
</template>
<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				// 弹框
				showModal: false,
				// 获取数据地址
				url_get_list: "~/api/laundry_order/get_list?like=0",
				url_del: "~/api/laundry_order/del?",

				// 字段ID
				field: "laundry_order_id",

				// 查询
				query: {
					"size": 7,
					"page": 1,
					"clothing_items_": "",
					"full_name": "",
					"order_status": "",
					"login_time": "",
					"create_time": "",
					"orderby": `create_time desc`
				},
				// 数据
				list: [],
				payModalVisible:false,
				pay_obj:{
					payActiveName:"微信",
					account:"",
					password:"",
					id:""
				},
				// 用户列表
				list_user_user_account: [],
				// 订单状态列表
				list_order_status: ['已取消','待取衣','已取衣','洗衣中','待送衣','送衣中','已送达'],
			}
		},
		methods: {
			// 关闭弹框
			closeModal(){
				this.showModal = false;
				},
			get_list_before(param){
				var user_group = this.user.user_group;
				if(user_group != "管理员"){
					switch(user_group){
						case "注册用户":
						param["user_account"] = this.user.user_id;
						break;
					}
				}
				return param;
			},

			open_tip() {
				const h = this.$createElement;

				var message = "";
				var list = this.list;

				var ifs = [ ];
				for (var n = 0; n < ifs.length; n++) {
					var o = ifs[n];
					for (var i = 0; i < list.length; i++) {
						var lt = list[i];
						if (o.type == "数内") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] > o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "数外") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日内") {
							if ((o.start) && (o.end)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日外") {
							if (o.start && o.end) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						}
					}

					if (o["idx"]) {
						message += o.title;
						if (o["type"] == "数内") {
							if (o.start || o.start === 0) {
								message += "大于" + o.start;
							}
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								message += "并且";
							}
							if (o.end || o.end === 0) {
								message += "小于" + o.end;
							}
						} else if (o["type"] == "数外") {
							if (o.start || o.start === 0) {
								message += "小于" + o.start;
							}
							if ((o.start || o.start === 0) || (o.end || o.end === 0)) {
								message += "或者";
							}
							if (o.end || o.end === 0) {
								message += "大于" + o.end;
							}
						} else if (o["type"] == "日内") {
							if (o.start) {
								message += "在" + o.start + "之后";
							}
							if (o.start && o.end) {
								message += "并且";
							}
							if (o.end) {
								message += "在" + o.end + "之前";
							}
						} else if (o["type"] == "日外") {
							if (o.start) {
								message += "在" + o.start + "之前";
							}
							if (o.start || o.end) {
								message += "或者";
							}
							if (o.end) {
								message += "在" + o.end + "之后";
							}
						}
						message += "的有" + o["idx"] + "条";
					}
				}

				if (message) {
					message += "，需要处理、请尽快处理。";
					this.showModal = true;
					// this.$notify({
					// 	title: '提醒',
					// 	dangerouslyUseHTMLString: true,
					// 	message: h('i', {
					// 		style: 'color: teal'
					// 	}, message)
					// });
				}
			},
			/**
			 * 获取注册用户用户列表
			 */
			async get_list_user_user_account() {
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
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
			openPayModal(v){
				this.pay_obj.id = v.laundry_order_id
				this.payModalVisible = true
			},
			submitPay(){
				let _this = this;
				let url = this.$toUrl(this.query, "~/api/laundry_order/set?laundry_order_id="+this.pay_obj.id);
				let param = {"pay_state":"已支付","pay_type":this.pay_obj.payActiveName}
				this.$post(url, param, function(json, status) {
					console.log("提交结果：" ,json);
					if (json.result) {
						_this.$toast('支付成功！', 'success');
						_this.payModalVisible = false;
						_this.get_list();
					} else if (json.error) {
						_this.$toast(json.error.message, 'danger');
					}
				});
			},
			deleteRow(index, rows) {
				rows.splice(index, 1);
			}

		},
		created() {
			this.get_list_user_user_account();
			setTimeout(() => {
				this.open_tip();
			}, 1000)
		}
	}
</script>

<style type="text/css">
	.bg {
		background: white;
	}

	.form.p_4 {
		padding: 1rem;
	}

	.form .el-input {
		width: initial;
	}

	.mt {
		margin-top: 1rem;
	}

	.text_center {
		text-align: center;
	}

	.float-right {
		float: right;
	}


	.modal_wrap{
		width: 100vw;
		height: 100vh;
		position: fixed;
		top: 0;
		left: 0;
		background: rgba(0,0,0,0.5);
		z-index: 9999999999;
	}
	.modal_wrap .modal_box{
		width: 400px;
		height: 200px;
		background: url("../../assets/modal_bg.jpg") no-repeat center;
		background-size: cover;
		position: absolute;
		top: 50%;
		left: 50%;
		margin-left: -200px;
		margin-top: -100px;
		border-radius: 10px;
		}
	.modal_wrap .modal_box .modal_box_close{
		font-size: 20px;
		position: absolute;
		top: 10px;
		right: 10px;
		cursor: pointer;
		}
	.modal_wrap .modal_box .modal_box_title{
	  text-align: center;
    font-size: 18px;
    margin: 16px auto;
    color: #fff;
    border-bottom: 1px solid rgba(117, 116, 116,0.5);
    padding-bottom: 16px;
    width: 356px;
		}
	.modal_wrap .modal_box .modal_box_text{
			text-align: center;
		font-size: 15px;
		color: #fff;
		margin-top: 25px;
		}
	.modal_wrap .modal_box .btn_box{
		display: flex;
		flex-direction: row;
		justify-content: center;
		margin-top: 42px;
		}
			.modal_wrap .modal_box .btn_box span{
				display: inline-block;
				width: 80px;
				height: 30px;
				line-height: 30px;
				text-align: center;
				border: 1px solid #ccc;
				font-size: 14px;
				cursor: pointer;
				color: #fff;
			}
	.modal_wrap .modal_box .btn_box span:nth-child(2){
		background: #409EFF;
		color: #fff;
		border-color: #409EFF;
		margin-left: 15px;
	}
</style>
