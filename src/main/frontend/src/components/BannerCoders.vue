<script setup>
	import { defineProps } from "vue";

	const props = defineProps({
		profile: Object,
		user: Object,
	});
</script>
<template>
	<section>
		<div class="infoProfile">
			<img
				class="photoCoder"
				:src="`http://localhost:8080/media/${props.user.image}`"
				alt="imagen user"
			/>
			<div class="contactsCoder">
				<div class="contactCoder">
					<h1 class="nameCoder">
						{{ profile.name }} {{ profile.surname }}
					</h1>
				</div>
				<div class="contactCoder">
					<img
						class="logo"
						src="../assets/images/imagesSomosF5/github.png"
						alt="gitHub"
					/>
					<p class="contactText">{{ profile.github }}</p>
				</div>
				<div class="contactCoder">
					<img
						class="logo"
						src="../assets/images/imagesSomosF5/linkedin.png"
						alt="linkedin"
					/>
					<p class="contactText">{{ profile.linkedin }}</p>
				</div>
				<div class="contactCoder">
					<img
						class="logo"
						src="../assets/images/imagesSomosF5/geo-alt.png"
						alt="geo"
					/>
					<p class="contactText">{{ profile.location }}</p>
				</div>
				<div class="contactCoder">
					<img
						class="logo"
						src="../assets/images/imagesSomosF5/Vector.png"
						alt="email"
					/>
					<p class="contactText">{{ user.username }}</p>
				</div>
			</div>
		</div>
		<div class="desingProfile">
			<img
				class="bannerDesing"
				src="../assets/images/imagesSomosF5/banner.png"
				alt=""
			/>
		</div>
	</section>
</template>
<style lang="scss">
	@use "@/scss/colors" as c;
	@use "@/scss/fonts";

	section {
		width: 100%;
		background-color: map-get(c.$colors, "orange");
		display: flex;
		align-items: center;
		justify-content: space-evenly;
		border-radius: 5px;

		.infoProfile {
			display: flex;
			width: 70%;
			align-items: center;
			justify-items: right;
			margin-left: 1%;
			.photoCoder {
				border-radius: 100%;
				max-width: 120px;
				max-height: 120px;
				object-fit: cover;
				width: 120px;
				height: 120px;
				margin: 1%;
			}
			.contactsCoder {
				width: 80%;
				display: flex;
				flex-direction: column;

				.contactCoder {
					display: flex;
					flex-direction: row;
					align-items: center;
					font-family: "openSans";
					margin-bottom: 0.5em;

					.nameCoder {
						color: white;
						font-family: "Open Sans", sans-serif;
						font-size: xx-large;
						font-weight: bolder;
					}
					.logo {
						width: 4%;
					}
					.contactText {
						color: white;
						font-family: "Open Sans", sans-serif;
						font-size: 1em;
						margin-left: 1%;
					}
				}
			}
		}
		.desingProfile {
			width: 30%;
			display: flex;
			flex-direction: column;
			flex-wrap: wrap;
			align-content: center;
			align-items: center;
			.bannerDesing {
				width: 100%;
			}
			.addContacts {
				width: 50%;
				border: solid;
				box-sizing: border-box;
				border-radius: 50px;
				color: map-get(c.$colors, "white");
				font-family: "openSans";
				font-weight: bold;
				font-size: 0.6em;
				text-align: center;
				margin-bottom: 4%;
				&:hover {
					background-color: map-get(c.$colors, "white");
					color: map-get(c.$colors, "orange");
				}
			}
		}
	}
</style>
