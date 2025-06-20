<template>
	<div class="diy_home diy_list diy_laundry_order" id="diy_laundry_order_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/laundry_order/details?laundry_order_id=' + o['laundry_order_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/laundry_order/details') && +item.is_img_list">
						<div class="diy_title diy_list_img_title">
							<span>{{item.title}}:</span>
						</div>
						<div class="diy_field diy_img">
							<img :src="$fullUrl(o[item.name])" style="width:100%;height:100%" />
						</div>
					</div>
				</div>
				<!-- 内容 -->
				<div class="diy_list_item_box">
					<div class="diy_list_item_content" v-for="(item,index) in showItemList" :key="item+index">
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/laundry_order/details') && +item.is_img_list">
							<div class="diy_title">
								<span>{{item.title}}:</span>
							</div>
							<div class="diy_field diy_text">
								<span v-if="item.type == 'UID'" v-text="get_user_name(o[item.name])"></span>
								<span v-else-if="item.type == '日期'" v-text="$toTime(o[item.name],'yyyy-MM-dd')"></span>
								<span v-else-if="item.type == '时间'" v-text="$toTime(o[item.name],'hh:mm:ss')"></span>
								<span v-else-if="item.type == '日长'" v-text="$toTime(o[item.name],'yyyy-MM-dd hh:mm:ss')"></span>
								<span v-else v-text="o[item.name]"></span>
							</div>
						</div>
					</div>
				</div>
			</router-link>
		</div>
		<!-- 表格 -->
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','clothing_items_')">衣物名称
					</th>
					<th class="diy_title" v-if="$check_field('get','clothes_type_')">衣物类型
					</th>
					<th class="diy_title" v-if="$check_field('get','service_price')">服务价格
					</th>
					<th class="diy_title" v-if="$check_field('get','user_account')">用户账号
					</th>
					<th class="diy_title" v-if="$check_field('get','full_name')">姓名
					</th>
					<th class="diy_title" v-if="$check_field('get','contact_number')">联系电话
					</th>
					<th class="diy_title" v-if="$check_field('get','receiving_address')">收取地址
					</th>
					<th class="diy_title" v-if="$check_field('get','time_of_appointment')">预约时间
					</th>
					<th class="diy_title" v-if="$check_field('get','order_status')">订单状态
					</th>
					<th class="diy_title" v-if="$check_field('get','order_number')">订单编号
					</th>
					<th class="diy_title" v-if="$check_field('get','matters_needing_attention')">注意事项
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
					<td class="diy_field diy_text" v-if="$check_field('get','clothing_items_')">
						<span>
							{{ o["clothing_items_"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','clothes_type_')">
						<span>
							{{ o["clothes_type_"] }}
						</span>
					</td>
					<td class="diy_field diy_number" v-if="$check_field('get','service_price')">
						<span>
							{{ o["service_price"] }}
						</span>
					</td>
					<td class="diy_field diy_uid" v-if="$check_field('get','user_account')">
						<span>
							{{ get_user_name(o['user_account']) }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','full_name')">
						<span>
							{{ o["full_name"] }}
						</span>
					</td>
					<td class="diy_field diy_phone" v-if="$check_field('get','contact_number')">
						<span>
							{{ o['contact_number'] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','receiving_address')">
						<span>
							{{ o["receiving_address"] }}
						</span>
					</td>
					<td class="diy_field diy_date" v-if="$check_field('get','time_of_appointment')">
						<span>
							{{ $toTime(o["time_of_appointment"] ,"yyyy-MM-dd") }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','order_status')">
						<span>
							{{ o["order_status"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','order_number')">
						<span>
							{{ o["order_number"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','matters_needing_attention')">
						<span>
							{{ o["matters_needing_attention"] }}
						</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
						imgList: [
						],
						itemList: [
								{
									title: "衣物名称",
									name: "clothing_items_",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "衣物类型",
									name: "clothes_type_",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "服务价格",
									name: "service_price",
									type: "数字",
									is_img_list: "0"
								},
								{
									title: "用户账号",
									name: "user_account",
									type: "UID",
									is_img_list: "0"
								},
								{
									title: "姓名",
									name: "full_name",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "联系电话",
									name: "contact_number",
									type: "手机",
									is_img_list: "0"
								},
								{
									title: "收取地址",
									name: "receiving_address",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "预约时间",
									name: "time_of_appointment",
									type: "日期",
									is_img_list: "0"
								},
								{
									title: "订单状态",
									name: "order_status",
									type: "下拉",
									is_img_list: "0"
								},
								{
									title: "订单编号",
									name: "order_number",
									type: "文本",
									is_img_list: "0"
								},
						],
						richList: [
								{
									title: "注意事项",
									name: "matters_needing_attention",
									type: "多文本"
								},
						],
				// 用户列表
				list_user_user_account: [],
			};
		},
		methods: {
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
			get_user_name(id){
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
		},
		created() {
			this.get_list_user_user_account();
		},
		computed:{
			showItemList(){
				let arr = [];
				let _type = ["视频","音频","文件"];
				this.itemList.forEach(item => {
					if(_type.indexOf(item.type) === -1 && !!+item.is_img_list){
						arr.push(item)
					}
				})
				return arr.slice(0,4);
			}
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>

