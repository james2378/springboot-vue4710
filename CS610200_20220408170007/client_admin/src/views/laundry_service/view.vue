<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','clothing_items_') || $check_field('add','clothing_items_') || $check_field('set','clothing_items_')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="衣物名称" prop="clothing_items_">
					<el-input id="clothing_items_" v-model="form['clothing_items_']" placeholder="请输入衣物名称"
							  v-if="user_group === '管理员' || (form['laundry_service_id'] && $check_field('set','clothing_items_')) || (!form['laundry_service_id'] && $check_field('add','clothing_items_'))" :disabled="disabledObj['clothing_items__isDisabled']"></el-input>
					<div v-else-if="$check_field('get','clothing_items_')">{{form['clothing_items_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','clothes_type_') || $check_field('add','clothes_type_') || $check_field('set','clothes_type_')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="衣物类型" prop="clothes_type_">
					<el-select id="clothes_type_" v-model="form['clothes_type_']"
						v-if="user_group === '管理员' || (form['laundry_service_id'] && $check_field('set','clothes_type_')) || (!form['laundry_service_id'] && $check_field('add','clothes_type_'))">
						<el-option v-for="o in list_clothes_type_" :key="o['clothes_type_']" :label="o['clothes_type_']"
							:value="o['clothes_type_']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','clothes_type_')">{{form['clothes_type_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','clothing_map') || $check_field('add','clothing_map') || $check_field('set','clothing_map')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="衣物图" prop="clothing_map">
					<el-upload :disabled="disabledObj['clothing_map_isDisabled']" id="clothing_map" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_clothing_map"
						:show-file-list="false" v-if="user_group === '管理员' || (form['laundry_service_id'] && $check_field('set','clothing_map')) || (!form['laundry_service_id'] && $check_field('add','clothing_map'))">
						<img v-if="form['clothing_map']" :src="$fullUrl(form['clothing_map'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','clothing_map')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['clothing_map'])" :preview-src-list="[$fullUrl(form['clothing_map'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','service_price') || $check_field('add','service_price') || $check_field('set','service_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="服务价格" prop="service_price">
					<el-input-number id="service_price" v-model.number="form['service_price']"
						v-if="user_group === '管理员' || (form['laundry_service_id'] && $check_field('set','service_price')) || (!form['laundry_service_id'] && $check_field('add','service_price'))"></el-input-number>
					<div v-else-if="$check_field('get','service_price')">{{form['service_price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','matters_needing_attention') || $check_field('add','matters_needing_attention') || $check_field('set','matters_needing_attention')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="注意事项" prop="matters_needing_attention">
					<el-input type="textarea" id="matters_needing_attention" v-model="form['matters_needing_attention']" placeholder="请输入注意事项"
						v-if="user_group === '管理员' || (form['laundry_service_id'] && $check_field('set','matters_needing_attention')) || (!form['laundry_service_id'] && $check_field('add','matters_needing_attention'))" :disabled="disabledObj['matters_needing_attention_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','matters_needing_attention')">{{form['matters_needing_attention']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','service_description') || $check_field('add','service_description') || $check_field('set','service_description')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="服务说明" prop="service_description">
					<el-input type="textarea" id="service_description" v-model="form['service_description']" placeholder="请输入服务说明"
						v-if="user_group === '管理员' || (form['laundry_service_id'] && $check_field('set','service_description')) || (!form['laundry_service_id'] && $check_field('add','service_description'))" :disabled="disabledObj['service_description_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','service_description')">{{form['service_description']}}</div>
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
				field: "laundry_service_id",
				url_add: "~/api/laundry_service/add?",
				url_set: "~/api/laundry_service/set?",
				url_get_obj: "~/api/laundry_service/get_obj?",
				url_upload: "~/api/laundry_service/upload?",

				query: {
					"laundry_service_id": 0,
				},

				form: {
					"clothing_items_":'', // 衣物名称
					"clothes_type_":'', // 衣物类型
					"clothing_map":'', // 衣物图
					"service_price":0, // 服务价格
					"matters_needing_attention":'', // 注意事项
					"service_description":'', // 服务说明
					"laundry_service_id": 0, // ID

				},
				disabledObj:{
					"clothing_items__isDisabled": false,
					"clothes_type__isDisabled": false,
					"clothing_map_isDisabled": false,
					"matters_needing_attention_isDisabled": false,
					"service_description_isDisabled": false,
				},
				//衣物类型选项列表
				list_clothes_type_: [],
			}
		},
		methods: {
			/**
			 * 获取衣物类型列表
			 */
			async get_list_clothes_type_() {
				var json = await this.$get("~/api/clothing_classification/get_list?");
				if(json.result && json.result.list){
					this.list_clothes_type_ = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传衣物图
			 * @param {Object} param图片参数
			 */
			upload_clothing_map(param){
				this.uploadFile(param.file, "clothing_map");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
					bl = this.$check_action('/laundry_service/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/laundry_service/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/laundry_service/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/laundry_service/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/laundry_service/view','get');
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
			this.get_list_clothes_type_();
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
