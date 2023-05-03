<script setup>
import { ref, reactive } from "vue";
import AuthService from "../services/AuthService";
import { useRouter } from "vue-router";
import { useProgrammatic } from '@oruga-ui/oruga-next'
	
const { oruga } = useProgrammatic()

let checkCodeVar = "F51234";

const router = useRouter();

const email = ref(""),
  emailRules = reactive([
    (v) => !!v || "Es necesario introducir un e-mail",
    (v) => /.+@.+/.test(v) || "El e-mail debe ser en el formato correcto",
  ]),
  password = ref(""),
  passwordRules = reactive([
    (v) => !!v || "Es necesario introducir una contraseña",
    (v) => v.length >= 8 || "La contraseña debe tener al menos 8 caracteres",
  ]),
  confirmPassword = ref(""),
  confirmPasswordRules = reactive([
    (v) => !!v || "Confirme su contraseña",
    (v) => v === password.value || "Las contraseñas no coinciden",
  ]),
  checkCode = ref(""),
  checkCodeRules = reactive([(v) => v === checkCodeVar || "¡Alerta, intruso!"]);

const submitData = async () => {
    const authService = new AuthService();
    if (email.value && password.value != null) {
        try {
        const response = await authService.register(email.value, password.value);
        oruga.notification.open({
      message: 'Enhorabuena! a partir de ahora ya formas parte de Somos f5',
      rootClass: 'success-notification',
      duration: 7000,
      position: 'top'
    });
        router.push("/");
    } catch (error) {
      console.error(error);
    }
  } else {
    alert("Debes rellenar todos los campos");
  }
};
</script>

<template>
    <div class="signIn">
        <h1 class="headerForm">Bienvenido a SomosF5</h1>
        <v-sheet class="mx-auto">
            <v-form v-model="valid" @submit.prevent="submitData">
                <v-text-field v-model="email" :rules="emailRules" label="Correo Electrónico" required></v-text-field>

        <v-text-field
          v-model="password"
          :rules="passwordRules"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="Contraseña"
          required
        >
        </v-text-field>

        <v-text-field
          v-model="confirmPassword"
          :rules="confirmPasswordRules"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="Repetir contraseña"
          required
        >
        </v-text-field>

                <v-text-field v-model="checkCode" :rules="checkCodeRules" label="Código de verificación" required>
                </v-text-field>

                <div class="d-flex flex-column">
                    <v-btn type="submit" class="mt-4" block @click="validate">
                        Registrarse
                    </v-btn>
                </div>
            </v-form>
        </v-sheet>
        <div class="design">
            <img class="blueSplash" src="../assets/images/svgPics/blueSplash.svg" />
            <img class="littleStar" src="../assets/images/svgPics/littleStar.svg" />
            <img class="blueTriangle" src="../assets/images/svgPics/blueTriangle.svg" />
        </div>
    </div>
</template>

<style lang="scss">
@use "@/scss/colors" as c;
@use "@/scss/fonts";

.signIn {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 2vh;

    .headerForm {
        background: url("../assets/images/separator1.png");
    background-size: cover;
    background-repeat: no-repeat;
        color: map-get(c.$colors, "white");
        font-family: 'openSans';
        font-weight: bold;
        font-size: 6vh;
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
        margin-top: 1%;

        .v-form {
            background-color: map-get(c.$colors, "orange");
            display: flex;
            flex-direction: column;
            height: 50%;

            .v-input__control {
                background-color: white;
                border-radius: 5px;
                z-index: 2;
            }

            .v-input__details {
                z-index: 5;

                .v-messages__message {
                    color: #FED2C0;
                }
            }

            .passwordLink {
                color: white;
                display: flex;
                justify-content: center;
                z-index: 2;
            }

            .d-flex.flex-column {
                width: fit-content;
                margin: auto;

                .v-btn {
                    color: white;
                    background-color: black;
                    z-index: 2;
                    margin-bottom: 5vh;
                }

                .v-btn--size-default {
                    width: 27vw;

                    .v-btn__content {
                        font-size: 2vh;
                    }
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
                height: 70vh;
                width: 50vw;

                .v-btn--size-default {
                    height: 7vh;
                    width: 27vw;

                    .v-btn__content {
                        font-size: 10vh;
                    }
                }
            }
        }
    }

    .design {
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
