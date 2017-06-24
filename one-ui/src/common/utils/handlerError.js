function handlerError (self, code, error) {
  if (code === 400) {
    handlerError400(self, error)
  } else if (code === 401) {
    handlerError401(self, error)
  }
}

/**
 * 处理400异常
 */
function handlerError400 (self, error) {
  console.error('Handler error 400')
  console.error(error)
  let errorCode = error.code
  if (errorCode === '1000') {
    // 处理验证失败错误
    let filedErrors = error.data
    for (let i = 0; i < filedErrors.length; i++) {
      self.$notify.danger(filedErrors[i].defaultMessage)
    }
  }
  if (error.message) {
    // 处理普通异常错误
    self.$notify.danger(error.message)
  }
}

/**
 * 处理401异常
 */
function handlerError401 (self, error) {
  console.error('Handler error 401')
  console.error(error)
  window.location.href = 'login.html'
}

export default handlerError
