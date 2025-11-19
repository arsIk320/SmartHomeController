require: slotfilling/slotFilling.sc
module = sys.zb-common
theme: /
state: Меню
q!: $regex</start|/menu|начать|меню>
a: Добро пожалуйста! Выберите действие:
  buttons:
    - title: "▶ Ручной режим"
      payload: "manual_mode"
    - title: "ℹ Статус"
      payload: "status_view"
    - title: "🌡 Погода"
      payload: "weather_view"
    - title: "🛡 Охрана"
      payload: "alarm_menu"

state: manual_mode
q!: $intent=manual_mode | $payload=manual_mode
a: Ручной режим активирован. Выберите действие:
  buttons:
    - title: "Свет ON/OFF"
      payload: "led_menu"
    - title: "Окно"
      payload: "window_menu"
    - title: "Сирена"
      payload: "buzzer_menu"
    - title: "Цвета"
      payload: "color_menu"
    - title: "Датчики"
      payload: "sensor_menu"
    - title: "⬅️ Назад"
      payload: "main_menu"

state: led_menu
q!: $intent=led_menu | $payload=led_menu
a: Управление светом:
  buttons:
    - title: "Включить"
      payload: "led_on"
    - title: "Выключить"
      payload: "led_off"
    - title: "⬅️ Назад"
      payload: "manual_mode"

state: led_on
q!: $intent=led_on | $payload=led_on
a: Выполняю: Включить свет
  api: 
    method: GET
    url: http://YOUR_ESP32_IP/led/on
  buttons:
    - title: "Выключить"
      payload: "led_off"
    - title: "⬅️ Назад"
      payload: "manual_mode"

state: led_off
q!: $intent=led_off | $payload=led_off
a: Выполняю: Выключить свет
  api: 
    method: GET
    url: http://YOUR_ESP32_IP/led/off
  buttons:
    - title: "Включить"
      payload: "led_on"
    - title: "⬅️ Назад"
      payload: "manual_mode"

# Добавьте остальные состояния аналогично:
# window_menu, window_open, window_close
# buzzer_menu, buzzer_on, buzzer_off
# color_menu, color_red, color_green, color_blue
# sensor_menu, и т.д.

state: status_view
q!: $intent=status_view | $payload=status_view
a: Получаю статус системы...
  api:
    method: GET
    url: http://YOUR_ESP32_IP/status
  response: Статус системы:\n{result}
  buttons:
    - title: "Обновить"
      payload: "status_view"
    - title: "⬅️ Назад"
      payload: "main_menu"

state: weather_view
q!: $intent=weather_view | $payload=weather_view
a: Получаю данные о погоде...
  api:
    method: GET
    url: http://YOUR_ESP32_IP/weather
  response: Погода:\n{result}
  buttons:
    - title: "Обновить"
      payload: "weather_view"
    - title: "⬅️ Назад"
      payload: "main_menu"

state: alarm_menu
q!: $intent=alarm_menu | $payload=alarm_menu
a: Управление охраной:
  buttons:
    - title: "Включить"
      payload: "alarm_on"
    - title: "Выключить"
      payload: "alarm_off"
    - title: "⬅️ Назад"
      payload: "main_menu"

state: alarm_on
q!: $intent=alarm_on | $payload=alarm_on
a: Включаю сигнализацию...
  api:
    method: GET
    url: http://YOUR_ESP32_IP/alarm/on
  buttons:
    - title: "Выключить"
      payload: "alarm_off"
    - title: "⬅️ Назад"
      payload: "main_menu"

state: alarm_off
q!: $intent=alarm_off | $payload=alarm_off
a: Выключаю сигнализацию...
  api:
    method: GET
    url: http://YOUR_ESP32_IP/alarm/off
  buttons:
    - title: "Включить"
      payload: "alarm_on"
    - title: "⬅️ Назад"
      payload: "main_menu"