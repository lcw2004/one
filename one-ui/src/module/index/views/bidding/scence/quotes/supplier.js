function totalMoney (supplierPrices) {
  let prices = supplierPrices.prices
  let suppliers = supplierPrices.suppliers
  let totalMoney = new Map()
  for (let value of prices) {
    let number = value.subjectInfo.number

    // 计算产品总价，并存到map中
    for (let supplier of suppliers) {
      let supplierId = supplier.id
      let containTax = value.supplierPrices[supplierId].containTax
      let subjectPrice = number * containTax
      let totalMoneyOfThisSupplier = totalMoney.get(supplierId)
      if (!totalMoneyOfThisSupplier) {
        totalMoneyOfThisSupplier = {}
        totalMoneyOfThisSupplier.total = 0
      } else {
        totalMoneyOfThisSupplier = totalMoney.get(supplierId)
      }
      totalMoneyOfThisSupplier.total += subjectPrice
      totalMoney.set(supplierId, totalMoneyOfThisSupplier)
    }
  }
  return totalMoney
}

function sortSupplierPrices (prices) {
  for (let value of prices.prices) {
    let supplierPrices = value.supplierPrices

    // 排序
    let supplierPricesValues = Object.values(supplierPrices)
    supplierPricesValues.sort(function (a, b) {
      let aTotal = a.containTax
      let bTotal = b.containTax
      if (aTotal < bTotal) return -1
      if (aTotal > bTotal) return 1
      return 0
    })

    // 根据顺序设置排名
    for (let i = 0; i < supplierPricesValues.length; i++) {
      supplierPricesValues[i].sortNum = i + 1
    }
  }
  return prices
}

function totalFloorMoney (prices) {
  let total = 0
  for (let value of prices.prices) {
    let subjectInfo = value.subjectInfo
    total += subjectInfo.floorPrice * subjectInfo.number
  }
  return total
}

function sortTotalMoney (totalMoney) {
  // 对总价进行排名
  let totalMoneyArray = Array.from(totalMoney.values())
  totalMoneyArray.sort(function (a, b) {
    let aTotal = a.total
    let bTotal = b.total
    if (aTotal < bTotal) return -1
    if (aTotal > bTotal) return 1
    return 0
  })

  // 设置名次
  for (let i = 0; i < totalMoneyArray.length; i++) {
    totalMoneyArray[i].sortNum = i + 1
  }
  return totalMoney
}

export {
  sortTotalMoney,
  sortSupplierPrices,
  totalFloorMoney,
  totalMoney
}
