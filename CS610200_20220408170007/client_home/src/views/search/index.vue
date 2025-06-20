<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="洗衣房资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registered_user/list', 'get')"
				:list="result_registered_user_full_name"
				title="注册用户姓名"
				source_table="registered_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registered_user/list', 'get')"
				:list="result_registered_user_id_number"
				title="注册用户身份证号"
				source_table="registered_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/delivery_clerk/list', 'get')"
				:list="result_delivery_clerk_full_name"
				title="投递员姓名"
				source_table="delivery_clerk"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/delivery_clerk/list', 'get')"
				:list="result_delivery_clerk_id_number"
				title="投递员身份证号"
				source_table="delivery_clerk"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/clothing_classification/list', 'get')"
				:list="result_clothing_classification_clothes_type_"
				title="衣物分类衣物类型"
				source_table="clothing_classification"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/laundry_service/list', 'get')"
				:list="result_laundry_service_clothing_items_"
				title="洗衣服务衣物名称"
				source_table="laundry_service"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/laundry_service/list', 'get')"
				:list="result_laundry_service_clothes_type_"
				title="洗衣服务衣物类型"
				source_table="laundry_service"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/laundry_order/list', 'get')"
				:list="result_laundry_order_clothing_items_"
				title="洗衣订单衣物名称"
				source_table="laundry_order"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/laundry_order/list', 'get')"
				:list="result_laundry_order_full_name"
				title="洗衣订单姓名"
				source_table="laundry_order"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/laundry_order/list', 'get')"
				:list="result_laundry_order_order_status"
				title="洗衣订单订单状态"
				source_table="laundry_order"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/cancellation_of_order/list', 'get')"
				:list="result_cancellation_of_order_clothing_items_"
				title="取消订单衣物名称"
				source_table="cancellation_of_order"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/cancellation_of_order/list', 'get')"
				:list="result_cancellation_of_order_full_name"
				title="取消订单姓名"
				source_table="cancellation_of_order"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/delivery_record/list', 'get')"
				:list="result_delivery_record_clothing_items_"
				title="投递记录衣物名称"
				source_table="delivery_record"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/delivery_record/list', 'get')"
				:list="result_delivery_record_full_name"
				title="投递记录姓名"
				source_table="delivery_record"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/delivery_record/list', 'get')"
				:list="result_delivery_record_dispatch_status"
				title="投递记录派送状态"
				source_table="delivery_record"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/application_postman/list', 'get')"
				:list="result_application_postman_audit_status"
				title="申请投递员审核状态"
				source_table="application_postman"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_registered_user_full_name":[],
			"result_registered_user_id_number":[],
			"result_delivery_clerk_full_name":[],
			"result_delivery_clerk_id_number":[],
			"result_clothing_classification_clothes_type_":[],
			"result_laundry_service_clothing_items_":[],
			"result_laundry_service_clothes_type_":[],
			"result_laundry_order_clothing_items_":[],
			"result_laundry_order_full_name":[],
			"result_laundry_order_order_status":[],
			"result_cancellation_of_order_clothing_items_":[],
			"result_cancellation_of_order_full_name":[],
			"result_delivery_record_clothing_items_":[],
			"result_delivery_record_full_name":[],
			"result_delivery_record_dispatch_status":[],
			"result_application_postman_audit_status":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取full_name
	 */
	get_registered_user_full_name(){
		this.$get("~/api/registered_user/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_user_full_name = json.result.list;
			result_registered_user_full_name.map(o => o.title = o['full_name'])
	  			this.result_registered_user_full_name = result_registered_user_full_name
		 	}
		});
	},
	/**
	 * 获取id_number
	 */
	get_registered_user_id_number(){
		this.$get("~/api/registered_user/get_list?like=0", { page: 1, size: 10, "id_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_user_id_number = json.result.list;
			result_registered_user_id_number.map(o => o.title = o['id_number'])
	  			this.result_registered_user_id_number = result_registered_user_id_number
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_delivery_clerk_full_name(){
		this.$get("~/api/delivery_clerk/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_delivery_clerk_full_name = json.result.list;
			result_delivery_clerk_full_name.map(o => o.title = o['full_name'])
	  			this.result_delivery_clerk_full_name = result_delivery_clerk_full_name
		 	}
		});
	},
	/**
	 * 获取id_number
	 */
	get_delivery_clerk_id_number(){
		this.$get("~/api/delivery_clerk/get_list?like=0", { page: 1, size: 10, "id_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_delivery_clerk_id_number = json.result.list;
			result_delivery_clerk_id_number.map(o => o.title = o['id_number'])
	  			this.result_delivery_clerk_id_number = result_delivery_clerk_id_number
		 	}
		});
	},
	/**
	 * 获取clothes_type_
	 */
	get_clothing_classification_clothes_type_(){
		this.$get("~/api/clothing_classification/get_list?like=0", { page: 1, size: 10, "clothes_type_": this.query.word }, (json) => {
		  if (json.result) {
			var result_clothing_classification_clothes_type_ = json.result.list;
			result_clothing_classification_clothes_type_.map(o => o.title = o['clothes_type_'])
	  			this.result_clothing_classification_clothes_type_ = result_clothing_classification_clothes_type_
		 	}
		});
	},
	/**
	 * 获取clothing_items_
	 */
	get_laundry_service_clothing_items_(){
		this.$get("~/api/laundry_service/get_list?like=0", { page: 1, size: 10, "clothing_items_": this.query.word }, (json) => {
		  if (json.result) {
			var result_laundry_service_clothing_items_ = json.result.list;
			result_laundry_service_clothing_items_.map(o => o.title = o['clothing_items_'])
	  			this.result_laundry_service_clothing_items_ = result_laundry_service_clothing_items_
		 	}
		});
	},
	/**
	 * 获取clothes_type_
	 */
	get_laundry_service_clothes_type_(){
		this.$get("~/api/laundry_service/get_list?like=0", { page: 1, size: 10, "clothes_type_": this.query.word }, (json) => {
		  if (json.result) {
			var result_laundry_service_clothes_type_ = json.result.list;
			result_laundry_service_clothes_type_.map(o => o.title = o['clothes_type_'])
	  			this.result_laundry_service_clothes_type_ = result_laundry_service_clothes_type_
		 	}
		});
	},
	/**
	 * 获取clothing_items_
	 */
	get_laundry_order_clothing_items_(){
		this.$get("~/api/laundry_order/get_list?like=0", { page: 1, size: 10, "clothing_items_": this.query.word }, (json) => {
		  if (json.result) {
			var result_laundry_order_clothing_items_ = json.result.list;
			result_laundry_order_clothing_items_.map(o => o.title = o['clothing_items_'])
	  			this.result_laundry_order_clothing_items_ = result_laundry_order_clothing_items_
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_laundry_order_full_name(){
		this.$get("~/api/laundry_order/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_laundry_order_full_name = json.result.list;
			result_laundry_order_full_name.map(o => o.title = o['full_name'])
	  			this.result_laundry_order_full_name = result_laundry_order_full_name
		 	}
		});
	},
	/**
	 * 获取order_status
	 */
	get_laundry_order_order_status(){
		this.$get("~/api/laundry_order/get_list?like=0", { page: 1, size: 10, "order_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_laundry_order_order_status = json.result.list;
			result_laundry_order_order_status.map(o => o.title = o['order_status'])
	  			this.result_laundry_order_order_status = result_laundry_order_order_status
		 	}
		});
	},
	/**
	 * 获取clothing_items_
	 */
	get_cancellation_of_order_clothing_items_(){
		this.$get("~/api/cancellation_of_order/get_list?like=0", { page: 1, size: 10, "clothing_items_": this.query.word }, (json) => {
		  if (json.result) {
			var result_cancellation_of_order_clothing_items_ = json.result.list;
			result_cancellation_of_order_clothing_items_.map(o => o.title = o['clothing_items_'])
	  			this.result_cancellation_of_order_clothing_items_ = result_cancellation_of_order_clothing_items_
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_cancellation_of_order_full_name(){
		this.$get("~/api/cancellation_of_order/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_cancellation_of_order_full_name = json.result.list;
			result_cancellation_of_order_full_name.map(o => o.title = o['full_name'])
	  			this.result_cancellation_of_order_full_name = result_cancellation_of_order_full_name
		 	}
		});
	},
	/**
	 * 获取clothing_items_
	 */
	get_delivery_record_clothing_items_(){
		this.$get("~/api/delivery_record/get_list?like=0", { page: 1, size: 10, "clothing_items_": this.query.word }, (json) => {
		  if (json.result) {
			var result_delivery_record_clothing_items_ = json.result.list;
			result_delivery_record_clothing_items_.map(o => o.title = o['clothing_items_'])
	  			this.result_delivery_record_clothing_items_ = result_delivery_record_clothing_items_
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_delivery_record_full_name(){
		this.$get("~/api/delivery_record/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_delivery_record_full_name = json.result.list;
			result_delivery_record_full_name.map(o => o.title = o['full_name'])
	  			this.result_delivery_record_full_name = result_delivery_record_full_name
		 	}
		});
	},
	/**
	 * 获取dispatch_status
	 */
	get_delivery_record_dispatch_status(){
		this.$get("~/api/delivery_record/get_list?like=0", { page: 1, size: 10, "dispatch_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_delivery_record_dispatch_status = json.result.list;
			result_delivery_record_dispatch_status.map(o => o.title = o['dispatch_status'])
	  			this.result_delivery_record_dispatch_status = result_delivery_record_dispatch_status
		 	}
		});
	},
	/**
	 * 获取audit_status
	 */
	get_application_postman_audit_status(){
		this.$get("~/api/application_postman/get_list?like=0", { page: 1, size: 10, "audit_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_application_postman_audit_status = json.result.list;
			result_application_postman_audit_status.map(o => o.title = o['audit_status'])
	  			this.result_application_postman_audit_status = result_application_postman_audit_status
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_registered_user_full_name();
		this.get_registered_user_id_number();
		this.get_delivery_clerk_full_name();
		this.get_delivery_clerk_id_number();
		this.get_clothing_classification_clothes_type_();
		this.get_laundry_service_clothing_items_();
		this.get_laundry_service_clothes_type_();
		this.get_laundry_order_clothing_items_();
		this.get_laundry_order_full_name();
		this.get_laundry_order_order_status();
		this.get_cancellation_of_order_clothing_items_();
		this.get_cancellation_of_order_full_name();
		this.get_delivery_record_clothing_items_();
		this.get_delivery_record_full_name();
		this.get_delivery_record_dispatch_status();
		this.get_application_postman_audit_status();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_registered_user_full_name();
	  this.get_registered_user_id_number();
	  this.get_delivery_clerk_full_name();
	  this.get_delivery_clerk_id_number();
	  this.get_clothing_classification_clothes_type_();
	  this.get_laundry_service_clothing_items_();
	  this.get_laundry_service_clothes_type_();
	  this.get_laundry_order_clothing_items_();
	  this.get_laundry_order_full_name();
	  this.get_laundry_order_order_status();
	  this.get_cancellation_of_order_clothing_items_();
	  this.get_cancellation_of_order_full_name();
	  this.get_delivery_record_clothing_items_();
	  this.get_delivery_record_full_name();
	  this.get_delivery_record_dispatch_status();
	  this.get_application_postman_audit_status();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
