<script setup>
import { ref, reactive } from "vue";

import { RouterLink, useRouter } from "vue-router";
import { useProgrammatic } from '@oruga-ui/oruga-next'
import AuthService from "../services/AuthService";
import { useAuthStore } from "../stores/AuthStore";

const auth = useAuthStore();
const router = useRouter();

const { oruga } = useProgrammatic()

const email = ref(""),

  emailRules = reactive([
    (v) => !!v || "Es necesario introducir un e-mail",
    (v) => /.+@.+/.test(v) || "Introduzca un e-mail válido",
  ]),
  password = ref(""),
  passwordRules = reactive([
    (v) => !!v || "Es necesario introducir una contraseña",
    (v) => v.length >= 8 || "La contraseña debe tener al menos 8 caracteres",

  ]);

const submitData = async () => {
  const authService = new AuthService();
  try {
    const user = await authService.login(email.value, password.value);
    auth.setRole(user.role);
    auth.setUsername(user.email);
    auth.setId(user.id);
    auth.setName(user.name);
    auth.setSurname(user.surname);
    auth.setLocation(user.location);
    auth.setGithub(user.github);
    auth.setLinkedin(user.linkedin);
    auth.setIsAuthenticated();

        oruga.notification.open({
      message: 'Bienvenido!',
      rootClass: 'success-notification',
      duration: 3000,
      position: 'top'
    });
    
    if(user.role == "ROLE_ADMIN"){
      router.push("/MuroAdmin/")
    }
    else{
      router.push("/ProfileUser/");
    }
    console.log(user);
  } catch (error) {
    console.error(error);

        oruga.notification.open({
      message: 'No te conozco',
      rootClass: 'success-notification',
      duration: 5000,
      position: 'top'
    });
  }
  onReset();
};
</script>

<template>
  <div class="logIn">
    <h1 class="headerForm">Bienvenido a SomosF5</h1>
    <v-sheet class="mx-auto">
      <v-form id="loginForm" @submit.prevent="submitData">

        <v-text-field v-model="email" :rules="emailRules" label="Correo electrónico" required>
        </v-text-field>

        <v-text-field v-model="password" :rules="passwordRules" :type="show1 ? 'text' : 'password'" name="input-10-1"
          label="Contraseña" required>
        </v-text-field>

        <!-- <a class="passwordLink" href="">¿Has olvidado tu contraseña?</a> -->

        <div class="d-flex flex-column">
          <v-btn type="submit" class="mt-4" block @click="validate">
            Enviar
          </v-btn>
        </div>
      </v-form>
    </v-sheet>
    <div class="designLG">
      <img class="blueSplash" src="../assets/images/svgPics/blueSplash.svg" />
      <img class="littleStar" src="../assets/images/svgPics/littleStar.svg" />
      <img class="blueTriangle" src="../assets/images/svgPics/blueTriangle.svg" />
    </div>
  </div>
</template>

<style lang="scss">
@use "@/scss/colors" as c;
@use "@/scss/fonts";

.logIn {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 7vh;

  .headerForm {
    
    background: url("../assets/images/separator1.png");
    background-size: cover;
    background-repeat: no-repeat;
    color: map-get(c.$colors,"white");
    font-family: 'openSans';
    font-weight: bold;
    font-size: 6vh;
    align-self: center;
    margin: 2%;
  }

  @media(max-width: 599px) {
    .headerForm {
      display: flex;
      justify-content: center;
      align-items: center;
      color: map-get(c.$colors, "white");
      font-family: 'openSans';
      font-weight: bold;
      font-size: 4vh;
    }
  }

  .v-sheet {
    width: 35vw;

    .v-form {
      background-color: map-get(c.$colors, "orange");

      .v-input__control {
        background-color: map-get(c.$colors, "white");
        border-radius: 5px;
        z-index: 5;
      }

      .passwordLink {
        color: map-get(c.$colors, "white");
        display: flex;
        justify-content: center;
      }

      .d-flex.flex-column {
        width: fit-content;
        margin: auto;

        .v-btn {
          color: map-get(c.$colors, "white");
          background-color: map-get(c.$colors, "black");
          z-index: 5;
        }

        .v-btn--size-default {
          min-width: 25vw;
        }
      }
    }
  }

  @media(max-width: 599px) {

    .v-sheet {
      width: 70vw;
      margin-top: 1%;

      .v-form {
        background-color: map-get(c.$colors, "orange");
        display: flex;
        flex-direction: column;
        margin: auto;
        height: 50%;
        width: 70vw;
      }
    }
  }

  @media(min-width: 1440px) {

    .v-sheet {
      width: 50vw;
      margin-top: 1%;

      .v-form {
        background-color: map-get(c.$colors, "orange");
        display: flex;
        flex-direction: column;
        margin: auto;
        height: 50vh;
        width: 50vw;

        .v-field__field{
          height: 10vh;
          font-size: 3vh;
        }

        .v-input__details{
          margin-bottom: 7vh;

          .v-messages__message{
            color: white;
            font-size: 2vh;
          }
        }

        .v-btn--size-default {
          height: 6vh;
          width: 27vw;

          .v-btn__content {
            font-size: 5vh;
          }
        }
      }
    }
  }


  .designLG {
    display: flex;
    position: absolute;
    bottom: 0;
    width: 100%;
    height: 50%;
    justify-content: space-between;
    align-items: flex-end;

    @media (min-width: 600px) {
      .blueTriangle {
        height: 100%;
        width: 18%;
      }

      .littleStar {
        position: relative;
        left: 30%;
        bottom: 35%;
        width: 12%;
      }

      .blueSplash {
        height: 85%;
        width: 25%;
      }
    }

    @media (max-width: 599px) {

      .blueTriangle {
        height: 0%;
        width: 0%;
      }

      .littleStar {
        bottom: 0%;
        width: 0%;
      }

      .blueSplash {
        height: 0%;
        width: 0%;
      }
    }
  }
}
</style>
