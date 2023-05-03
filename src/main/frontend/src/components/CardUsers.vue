<script setup>
let random = Math.round(Math.random() * 2 + 1);
let image = "src/assets/images/separator" + random + ".png";

const props = defineProps({
  user: Object,
  profile: Array,
});

const profileDescription = () => {
  router.push(`username/${props.user.username}`);
};
</script>

<template>
  <section
    class="card-u"
    :class="{ cyan: random === 1, purple: random === 2, orange: random === 3 }"
  >
    <div class="info-u">
      <img
        class="img-u"
        v-if="user.image != null"
        :src="'http://localhost:8080/media/' + user.image"
      />
      <img
        v-else
        class="img-u"
        src="../assets/images/perfilVacio.png"
        alt="img"
      />
      <div class="date-u">
        <h1 class="name-u">
          {{ profile[user.id - 1].name + profile[user.id - 1].surname }}
        </h1>
        <div class="contact-u">
          <i class="fa-regular fa-envelope logo-u" style="color: #000000"></i>
          <p @click="profileDescription" class="text-u">{{ user.username }}</p>
        </div>
        <div class="contact-u">
          <i class="fa-solid fa-location-dot" style="color: #000000"></i>
          <p class="text-u">{{ profile[user.id - 1].location }}</p>
        </div>
      </div>
    </div>
  </section>

  <div class="separator-u" id="separator">
    <img
      class="stripe-u"
      :src="image"
      alt="Línea separadora de color morado."
    />
  </div>
</template>
<style lang="scss">
@use "@/scss/colors" as c;
@use "@/scss/fonts";

.cyan {
  background-color: map-get(c.$colors, "light-green-tr");
}

.orange {
  background-color: map-get(c.$colors, "orange-tr");
}

.purple {
  background-color: map-get(c.$colors, "light-purple-tr");
}
.card-u {
  width: 80%;
  margin-top: 2%;
  display: flex;
  align-items: flex-end;
  justify-content: space-between;

  .info-u {
    display: flex;
    align-items: center;

    .img-u {
      border-radius: 100%;
      width: 15vh;
      margin: 1vh 3vh 1vh 5vh;
    }
    .date-u {
      margin-top: 1%;

      .name-u {
        font-family: "Open Sans", sans-serif;
        font-weight: bold;
        font-size: 1.8vw;
      }
      .contact-u {
        display: flex;
        align-items: center;

        i {
          margin: 1%;
        }
      }
    }
  }

  .btn-u {
    background-color: map-get(c.$colors, "orange");
    color: map-get(c.$colors, "white");
    font-family: "Open Sans", sans-serif;
    font-size: 60%;
    width: 5%;
    border: solid;
    box-sizing: border-box;
    border-radius: 100%;
    margin: 1%;

    &:hover {
      background-color: map-get(c.$colors, "green");
      color: map-get(c.$colors, "orange");
    }

    .trash-u {
      margin-bottom: 1vh;
    }
  }
}

.separator-u {
  display: flex;
  justify-content: center;
  align-items: end;
  margin: 2vw;

  .stripe-u {
    width: 60vw;
    height: 4vh;
  }
}
</style>
