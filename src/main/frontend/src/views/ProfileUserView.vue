<script setup>
	import { ref, onBeforeMount } from "vue";
	import { useProgrammatic } from "@oruga-ui/oruga-next";
	import { useAuthStore } from "../stores/AuthStore";
	import PostService from "../services/PostService"
	import ProfileService from "../services/ProfileService";
	import Header from "../components/Header.vue";
	import AddPublication from "../components/AddPublication.vue";
	import InfoUser from "../components/InfoUser.vue";
 	import CardProfile from "../components/CardProfile.vue";
	 
	 
	const store = useAuthStore()
	const profileService = new ProfileService();
	const postService = new PostService();
	
	let profile = ref();
		
	onBeforeMount(async()=>{
		await profileService.fetchOneProfile(store.id)
		profile.value = profileService.getProfile()
		console.log(profile.value)
	});
	
	onBeforeMount(async () => {
		await postService.fetchAllPost();
		posts.value = postService.getPost();
		console.log(posts.value);
	});
	


	


	const trapFocus = ref(false);
	const { oruga } = useProgrammatic();

	function cardModal() {
		oruga.modal.open({
			component: AddPublication,
			trapFocus: true,
		});
	}
</script>

<template>
	<main>
		<Header />
		<InfoUser />
		<div class="title">
			<div class="text">
				<h2>Mis publicaciones</h2>
			</div>
			<img
				class="star"
				src="https://uploads-ssl.webflow.com/62e2b7b9c42bdda27c83d493/6329c1bcb276576e8a46f894_shape-2.svg"
				alt="Imagen de una estrella color lila."
			/>
		</div>
		<section class="modal-container">
			<o-button
				@click="cardModal()"
				class="modal"
			>
				AÑADIR PUBLICACION
				<i class="fa-solid fa-plus btn-add"></i>
			</o-button>
	
		</section>

		
			<CardProfile
			v-for= "post in profile.posts" :post="post"/>
		
	
	</main>
</template>

<style lang="scss" scoped>
	@use "@/scss/colors" as c;
	@use "@/scss/fonts";
	
	main {
		margin: 0 auto;
		width: 80%;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
	}
	.title {
		width: 46vw;
		display: flex;
		align-items: center;
		justify-content: center;
		margin-top: 1em;

		.text {
			font-size: xx-large;
			color: map-get(c.$colors, "black");
			font-family: "Open Sans", sans-serif;
			font-weight: bold;
			z-index: 1;
		}

		.star {
			position: relative;
			width: 5%;
			right: 3%;
		}
	}


	.modal-container {
		align-self: start;
		display: flex;
		justify-content: space-between;
		width: 100%;

		
		.modal {
			border-radius: 5px;
			background: map-get(c.$colors, "white");
			margin-bottom: 10vh;
			display: flex;
			border: 2px solid black;
			width: 25vw;
			font-size: 1.2em;
			color: black;
			height: 2em;
			
			.btn-add {
				margin-left: 1em;
			}
			.input-search{
				display:flex;
				background-color: map-get(c.$colors, "light-purple");
				border-radius: 5px;
				align-items: center;
				height: 2.8em;
				width: 20vw;
	
				i{
					font-size: 1.1em;
					margin: .3em;
				}
			}
	
			input {
				outline: none;
			}
		}
	}

	@media (max-width: 599px) {
		.title {
			width: 80%;
			display: flex;
			align-items: center;
			justify-content: center;
			.text {
				font-size: 1.2rem;
			}

			.star {
				width: 12%;
				right: 7%;
			}
		}
		.modal-container {
			margin-top: 2%;
			width: 60%;
			font-size: 50%;
		}
	}
</style>

