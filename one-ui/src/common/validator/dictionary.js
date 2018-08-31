const dictionary = {
  zh_CN: {
    messages: {
      'identityCode': (field) => `请输入正确的身份证号码`,
      'mobile': (field) => `请输入11位手机号码`,
      'phone': (field) => `请输入正确的电话号码`,
      'fax': (field) => `请输入正确的传真号码`,
      'webSite': (field) => `请输入正确的网址`,
      'zipCode': (field) => `请输入正确的邮政编码`,
      'password': (field) => `请输入6-20位包含数字和字符的密码`,
      'officeCode': (field) => `请输入正确的${field}`,
      'bankCard': (field) => `请输入正确的${field}`,
      'oneTimeAfter': (field, args) => `${field}必须在${args[0]}之后`
    }
  }
}

export default dictionary
