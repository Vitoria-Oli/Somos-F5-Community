<script setup>
	import { ref, reactive, computed } from "vue";
	import axios from "axios";
	import ElMuroView from "./ElMuroView.vue";

	const url = ref("");
	const imageUrl = computed(() => url.value);

	const onFileChange = event => {
		const file = event.target.files[0];
		if (file) {
			const formData = new FormData();
			formData.append("file", file);
			axios({
				method: "POST",
				url: "http://localhost:8080/media/upload",
				data: formData,
				withCredentials: true

			})
				.then(response => {
					url.value = response.data.url;
                    console.log(url.value)
				})
				.catch(e => {
					console.log(e);
				});
		}
	};
</script>

<template>
	<ElMuroView />
	<h1>Hola</h1>
	<form
		method="post"
		class="form-film"
		@submit.prevent="submit"
	>
		<h1 class="form-film__title">Añade un nuevo título</h1>
		<input
			type="text"
			name="title"
			placeholder="Título"
			class="form-film__input"
			v-model="titleModel"
		/>
		<input
			type="file"
			name="file"
			class="file-img form-film__input"
			@change="onFileChange"
		/>
		<img
			v-if="url"
			class="img"
			:src="imageUrl"
			alt=""
		/>
		<button type="submit">Submit</button>
	</form>
</template>
<style lang="scss">
	button {
		background: red;
		padding: 1em;
		width: 50%;
	}
</style>
