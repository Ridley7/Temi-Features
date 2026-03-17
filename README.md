# 🌱 Prototipo de Aplicación Ambiental

![Pantalla principal del prototipo](/documentation/screen_app.jpeg)

## 📱 Descripción general

Este proyecto consiste en una aplicación Android desarrollada en **Kotlin con Jetpack Compose**, cuyo objetivo es monitorizar condiciones ambientales en tiempo real y ofrecer recomendaciones inteligentes al usuario.

La app integra sensores y procesamiento de datos para detectar situaciones potencialmente peligrosas (como gases, humo o ruido excesivo) y actúa mediante alertas y sugerencias.

---

## 🚀 Funcionalidades principales

### 🔍 Monitorización del entorno
- Lectura continua de variables ambientales
- Uso de `StateFlow` para observar cambios en tiempo real
- Representación de datos en UI reactiva con Jetpack Compose

### 🤖 Sistema de recomendaciones
- Generación de recomendaciones basadas en condiciones detectadas
- Evaluación de niveles de riesgo (LOW, MEDIUM, HIGH)
- Mensajes claros para el usuario (ej: evacuar, ventilar, etc.)

### ⚠️ Sistema de alertas
- Detección de situaciones críticas (gases, humo, CO, etc.)
- Respuestas estructuradas en JSON
- Interpretación de severidad y activación de alertas

### 🧍 Avatar interactivo
- Gestión de estado mediante `AvatarStateManager`
- Representación visual del estado del entorno
- Posible integración con sistemas de atención o feedback

---

## 🧠 Arquitectura

El proyecto sigue una arquitectura moderna basada en **MVVM (Model-View-ViewModel)**:

- **ViewModel**: Maneja el estado y la lógica de negocio
- **UseCases**:
  - `ObserveEnvironmentUseCase`
  - `GetEnvironmentRecommendationUseCase`
- **Managers**:
  - `AttentionManager`
  - `AvatarStateManager`

### Ejemplo de ViewModel

```kotlin
class EnvironmentViewModel(
    private val observeEnvironmentUseCase: ObserveEnvironmentUseCase,
    private val recommendationUseCase: GetEnvironmentRecommendationUseCase,
    private val attentionManager: AttentionManager,
    private val avatarManager: AvatarStateManager
) : ViewModel() {

    val avatarState : StateFlow<AvatarState> = avatarManager.state

    val environment = observeEnvironmentUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null
        )
}
```

---

## 🎨 Interfaz de usuario (Jetpack Compose)

### 🧩 Pantalla principal
- Uso de `Row`, `Column` y `Card`
- Distribución responsiva
- Componentes reutilizables como `SensorItem`

### 🧱 Ejemplo de componente

```kotlin
@Composable
fun SensorItem(
    label: String,
    value: String
){
    Card (
        modifier = Modifier.fillMaxWidth()
    ){
        Row (
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(label)

            Text(
                value,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
```

### 🔘 Botones
- Uso de `Button` con `Modifier.fillMaxWidth()`
- Ajustes de alineación de texto

---

## 🔧 Tecnologías utilizadas

- **Kotlin**
- **Jetpack Compose**
- **StateFlow / Coroutines**
- **MVVM Architecture**
- **Koin (inyección de dependencias)**
- **Retrofit (preparado para networking)**
- **Lottie (para animaciones)**

---

## 📊 Procesamiento de datos

La aplicación interpreta respuestas estructuradas como:

```json
{
  "alert": true,
  "severity": "HIGH",
  "message": "Los niveles de humedad, sonido, gases inflamables, alcohol, monóxido de carbono, humo y vapores volátiles están peligrosamente altos."
}
```

Estas respuestas son procesadas para:
- Activar alertas
- Actualizar el estado del avatar
- Mostrar recomendaciones al usuario

---

## ⚙️ Retos técnicos abordados

- Gestión de estados reactivos con múltiples fuentes
- Interpretación de respuestas JSON desde sistemas externos
- Problemas de UI en Compose (alineación, espaciado, orientación)
- Configuración de dependencias y networking

---

## 📈 Futuras mejoras

- Integración con sensores reales (IoT)
- Persistencia de datos (Room)
- Dashboard con gráficos históricos
- Notificaciones push
- Optimización para múltiples dispositivos y orientaciones

---

## 📌 Estado del prototipo

🚧 Terminado

---

## 🤝 Contribuciones

No se aceptan contribuciones, pero sientete libre de tomar lo que necesites.

---


