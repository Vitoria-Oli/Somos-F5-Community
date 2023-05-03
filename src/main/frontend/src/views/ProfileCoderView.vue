<script setup>
	import Header from "../components/Header.vue";
	import BannerCoders from "../components/BannerCoders.vue";
	import { useRoute } from "vue-router";
	import { ref, onBeforeMount } from "vue";
	import axios from "axios";
	import UserService from "../services/UserService";

	const service = new UserService();

	const route = useRoute();
	const profile = ref();
	const user = ref();
	onBeforeMount(async () => {
		const id = route.params.username;

    await service.fetchOneUser(id)
    user.value = service.getUser()
    console.log(user.value)



		try {
			const response = await axios.get(
				`http://localhost:8080/api/profiles/${id}`
			);
			console.log(response.data);
			profile.value = response.data;
		} catch (error) {
			console.log(error);
		}
	});
</script>
<template>
	<main>
		<Header />
		<BannerCoders
			:profile="profile"
			:user="user"
		/>
		<div class="titleView">
			<!-- <h1 class="titleText">Todas las publicaciones</h1> -->
			{{ user.value }}
		</div>
	</main>
</template>
<style lang="scss">
	@use "@/scss/colors" as c;
	@use "@/scss/fonts";

	main {
		margin: 0 auto;
		width: 80%;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;

		.titleView {
			width: 70%;
			margin: 3%;
			background-image: url(../assets/images/separatorTurquesa.png);
			background-size: 100%;
			display: flex;
			align-items: end;
			justify-content: center;
			.titleText {
				color: white;
				font-family: "Open Sans", sans-serif;
				font-size: xx-large;
				font-weight: bold;
			}
		}
	}
</style>
