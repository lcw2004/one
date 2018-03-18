const dictionary = {
  zh_CN: {
    messages: {
      'identityCode': (field) => `请输入正确的身份证号码`,
      'mobile': (field) => `必须是11位手机号码`,
      'password': (field) => `请输入6-20位包含数字和字符的密码`,
      'officeCode': (field) => `请输入正确的社会信用代码`,
      'oneTimeAfter': (field, args) => `${field}必须在${args[0]}之后`
    }
  }
}

export default dictionary
