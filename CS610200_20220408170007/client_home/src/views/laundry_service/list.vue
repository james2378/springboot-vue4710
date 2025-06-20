<template>
	<div class="diy_list page_laundry_service" id="laundry_service_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">洗衣服务列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">							
							<span class="diy_list_search_title">关键字搜索：</span>
							<b-form-input size="sm" class="mr-sm-2" placeholder="衣物名称搜索" v-model="query['clothing_items_']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="衣物类型搜索" v-model="query['clothes_type_']" />
							<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
								<b-dropdown text="衣物类型" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','clothes_type_')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_clothes_type_" :key="i" @click="filter_set(o['clothes_type_'],'clothes_type_')" >
												{{ o['clothes_type_'] }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>
				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_laundry_service :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
<!--						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>-->
            <b-pagination
                v-model="query.page"
                :total-rows="count"
                :per-page="query.size"
                @change="goToPage"
            />
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_laundry_service from "@/components/diy/list_laundry_service.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_laundry_service
		},
		data() {
			return {
				url_get_list: "~/api/laundry_service/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"clothing_items_": "", // 衣物名称
					"clothes_type_": "", // 衣物类型
				},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "`create_time` desc",
					},
					{
						name: "创建时间从低到高",
						value: "`create_time` asc",
					},
					{
						name: "更新时间从高到低",
						value: "`update_time` desc",
					},
					{
						name: "更新时间从低到高",
						value: "`update_time` asc",
					},
					{
						name: "衣物名称正序",
						value: "`clothing_items_` asc",
					},
					{
						name: "衣物名称倒序",
						value: "`clothing_items_` desc",
					},
					{
						name: "衣物类型正序",
						value: "`clothes_type_` asc",
					},
					{
						name: "衣物类型倒序",
						value: "`clothes_type_` desc",
					},
				],
				// 衣物类型列表
				"list_clothes_type_": [],
			}
		},
		methods: {
      get_list_before(param) {
      },
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},
			/**
			 * 获取衣物类型列表
			 */
			async get_list_clothes_type_() {
				var json = await this.$get("~/api/clothing_classification/get_list?");
				if (json.result) {
					this.list_clothes_type_ = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},

			/**
			 * 筛选
			 */
			filter_clothes_type_(o) {
				if (o == "全部") {
					this.query["clothes_type_"] = "";
				} else {
					this.query["clothes_type_"] = o;
				}
				this.search();
			},

			/**
			 * 重置
			 */
			reset() {
				this.query.clothing_items_ = ""
				this.query.clothes_type_ = ""
				this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},

      goToPage(v){
        this.query.page = v;
        this.goToNew(v)
      },
		},
		computed: {
		},
		created() {
			/**
			 * 获取衣物类型列表
			 */
			this.get_list_clothes_type_();
		}
	}
</script>

<style>
</style>
