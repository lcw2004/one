/**
 * SockJS 文档
 * http://jmesnil.net/stomp-websocket/doc/
 */
const SockJS = window.SockJS
const Stomp = window.Stomp
const ONE_WEBSOCKET = '/one-websocket'
let stompClient = null
let subscribeList = []

/**
 * 链接Socket
 * @param {Function} onConnect [初始化成功之后的回掉函数，参数是stompClient]
 */
function connect (onConnect) {
  // 如果链接状态正常，退出，不初始化
  if (stompClient && stompClient.connected) {
    return
  }

  let socket = new SockJS(ONE_WEBSOCKET)
  stompClient = Stomp.over(socket)

  stompClient.connect({}, (frame) => {
    console.log('Connected: ' + frame)

    // 回调Socket连接成功事件
    if (onConnect) {
      onConnect(stompClient)
    }

    // 订阅列表
    subscribeAll()
  })
}

/**
 * 订阅主题，如果已经Socket已经准备好，则直接订阅，如果未准备好，则会在连接成功之后统一订阅
 * @param  {[type]}   topic       [订阅的主题]
 * @param  {Function} onSubscribe [订阅成功之后的回调事件]
 * @param  {Function} onMessage   [接收到消息之后的回调事件]
 */
function subscribe (topic, onSubscribe, onMessage) {
  if (isConnect()) {
    let subscription = stompClient.subscribe(topic, onMessage)
    subscribeList.push({
      topic,
      onSubscribe,
      onMessage,
      subscription: subscription,
      isSub: true
    })

    // 回调订阅成功之后的事件，传入订阅对象
    if (onSubscribe) {
      onSubscribe(subscription)
    }
  } else {
    subscribeList.push({
      topic,
      onSubscribe,
      onMessage,
      subscription: null,
      isSub: false
    })
  }
}

function subscribeAll () {
  if (subscribeList && subscribeList.length > 0) {
    for (let sub of subscribeList) {
      let subscription = stompClient.subscribe(sub.topic, sub.onMessage)
      sub.subscription = subscription
      sub.isSub = true

      // 回调订阅成功之后的事件，传入订阅对象
      if (sub.onSubscribe) {
        sub.onSubscribe(subscription)
      }
    }
  }
}

/**
 * 返回连接状态
 */
function isConnect () {
  return stompClient && stompClient.connected
}

/**
 * 断开链接
 */
function disConnect () {
  if (stompClient) {
    stompClient.disconnect()
  }
}

export default {
  connect,
  isConnect,
  disConnect,
  subscribe
}
