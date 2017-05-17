const data = {
  suppliers: [
    {
      id: '1',
      name: '南京双灵瑞电力自动化系统有限公司'
    },
    {
      id: '2',
      name: '宜兴市博发自动化设备有限公司'
    },
    {
      id: '3',
      name: '安徽康创系统工程有限公司'
    }
  ],
  prices: [
    {
      subjectInfo: {
        name: '#1~2污泥处置电控柜',
        code: '001',
        number: 2,
        price: 11,
        floorPrice: 6000,
        unit: '面'
      },
      supplierPrices: {
        '1': {
          supplierId: '1',
          containTax: 6004,
          notContainTax: 5131,
          tax: 17
        },
        '2': {
          supplierId: '2',
          containTax: 6192,
          notContainTax: 5292,
          tax: 17
        },
        '3': {
          supplierId: '3',
          containTax: 5680,
          notContainTax: 4854,
          tax: 17
        }
      }
    },

    {
      subjectInfo: {
        name: '包装费',
        code: '002',
        number: 2,
        price: 12,
        floorPrice: 150,
        unit: '面'
      },
      supplierPrices: {
        '1': {
          supplierId: '1',
          containTax: 295,
          notContainTax: 252,
          tax: 17
        },
        '2': {
          supplierId: '2',
          containTax: 50,
          notContainTax: 42,
          tax: 17
        },
        '3': {
          supplierId: '3',
          containTax: 194,
          notContainTax: 165,
          tax: 17
        }
      }
    },

    {
      subjectInfo: {
        name: '运费',
        code: '003',
        number: 1,
        price: 13,
        floorPrice: 300,
        unit: '次'
      },
      supplierPrices: {
        '1': {
          supplierId: '1',
          containTax: 492,
          notContainTax: 420,
          tax: 17
        },
        '2': {
          supplierId: '2',
          containTax: 500,
          notContainTax: 427,
          tax: 17
        },
        '3': {
          supplierId: '3',
          containTax: 291,
          notContainTax: 248,
          tax: 17
        }
      }
    }
  ]
}
export default data
