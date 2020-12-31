/**
 *数组相关方法
 链接：https://www.w3school.com.cn/jsref/jsref_obj_array.asp
 concat|join|pop|push|reverse|shift|unshift|slice|sort|splice|toString|valueOf
 */

/**
 * concat()方法用户连接两个或多个数组
 * 该方法不会改变现有的数组，而仅仅会返回被连接数组的一个副本
 * 语法：
 * arrayObject.concat(arrayX,arrayX,......,arrayX)
 * 
 */
console.log('---concat---')
let a = [1, 2, 3]
let b = [4, 5, 6]
let c = [7, 8, 9]
let e = []
let f = []

f = e.concat(a, b, c)
console.log(e)
console.log(f)


/**
 * join() 方法用于把数组中的所有元素放入一个字符串。
 * 元素是通过指定的分隔符进行分隔的。
 * 语法：
 * arrayObject.join(separator)
 */
console.log('---join---')
let arr = ['one', 'two', 'three']
let str = arr.join('|')
console.log(str)

/**
 * pop() 方法用于删除并返回数组的最后一个元素。
 * pop() 方法将删除 arrayObject 的最后一个元素，把数组长度减 1，并且返回它删除的元素的值。
 * 如果数组已经为空，则 pop() 不改变数组，并返回 undefined 值。
 * 语法：
 * arrayObject.pop()
 * 
 */
console.log('---pop---')
let arr1 = arr.pop()
console.log(arr)
console.log(arr1)

/**
 * push() 方法可向数组的末尾添加一个或多个元素，并返回新的长度
 * 语法：
 * arrayObject.push(newelement1,newelement2,....,newelementX)
 * 
 */
console.log('---push---')
let len = arr.push('four')
console.log(len)
console.log(arr)


/**
 * shift() 方法用于把数组的第一个元素从其中删除，并返回第一个元素的值。
 * 如果数组是空的，那么 shift() 方法将不进行任何操作，返回 undefined 值。
 * 请注意，该方法不创建新数组，而是直接修改原有的 arrayObject。
 * 语法：
 * arrayObject.shift()
 * 
 */
console.log('---shift---')
let first = arr.shift()
console.log(first)
console.log(arr)


/**
 * unshift() 方法可向数组的开头添加一个或更多元素，并返回新的长度。
 * unshift() 方法将把它的参数插入 arrayObject 的头部，并将已经存在的元素顺次地移到较高的下标处，以便留出空间。
 * 该方法的第一个参数将成为数组的新元素 0，如果还有第二个参数，它将成为新的元素 1，以此类推。
 * 请注意，unshift() 方法不创建新的创建，而是直接修改原有的数组。
 * 语法：
 * arrayObject.unshift(newelement1,newelement2,....,newelementX)
 * 
 */

console.log('---unshift---')
let len2 = arr.unshift('five', 'six', 'seven')
console.log(len2)
console.log(arr)

/**
 * slice() 方法可从已有的数组中返回选定的元素。
 * 语法：
 * arrayObject.slice(start,end)
 * start:必需。规定从何处开始选取。如果是负数，那么它规定从数组尾部开始算起的位置。也就是说，-1 指最后一个元素，-2 指倒数第二个元素，以此类推。
 * end:可选。规定从何处结束选取。该参数是数组片断结束处的数组下标。如果没有指定该参数，那么切分的数组包含从 start 到数组结束的所有元素。如果这个参数是负数，那么它规定的是从数组尾部开始算起的元素。
 * 返回一个新的数组，包含从 start 到 end （不包括该元素）的 arrayObject 中的元素。
 * 
 */

console.log('---slice---')
console.log(arr)
let arr2 = arr.slice(1, 3)
console.log(arr2)
let arr3 = arr.slice(1)
console.log(arr3)
let arr4 = arr.slice(-3,-1)
console.log(arr4)
let arr5 = arr.slice(-3)
console.log(arr5)


/**
 * splice() 方法向/从数组中添加/删除项目，然后返回被删除的项目。
 * 该方法会改变原始数组。
 * 语法：
 * arrayObject.splice(index,howmany,item1,.....,itemX)
 * index： arrayObject.splice(index,howmany,item1,.....,itemX)
 * howmany： 必需。要删除的项目数量。如果设置为 0，则不会删除项目。
 * item1, ..., itemX： 可选。向数组添加的新项目。
 * 
 * splice() 方法可删除从 index 处开始的零个或多个元素，并且用参数列表中声明的一个或多个值来替换那些被删除的元素。
 * 如果从 arrayObject 中删除了元素，则返回的是含有被删除的元素的数组。
 */


console.log('---splice---')
console.log(arr)
let arr6 = arr.splice(2,1,'splice1','splice2')
console.log(arr)
console.log(arr6)


/**
 * sort() 方法用于对数组的元素进行排序。
 * 语法：
 * arrayObject.sort(sortby)
 * 
 * 对数组的引用。请注意，数组在原数组上进行排序，不生成副本。
 * 如果调用该方法时没有使用参数，将按字母顺序对数组中的元素进行排序，说得更精确点，是按照字符编码的顺序进行排序。
 * 要实现这一点，首先应把数组的元素都转换成字符串（如有必要），以便进行比较。
 * 
 * 如果想按照其他标准进行排序，就需要提供比较函数，该函数要比较两个值，然后返回一个用于说明这两个值的相对顺序的数字。
 * 比较函数应该具有两个参数 a 和 b，其返回值如下：
 * 若 a 小于 b，在排序后的数组中 a 应该出现在 b 之前，则返回一个小于 0 的值。
 * 若 a 等于 b，则返回 0。
 * 若 a 大于 b，则返回一个大于 0 的值。
 * 
 */

console.log('---sort---')

let arr7 = [5,2,6,8,4,5,2,9]
console.log(arr7)
arr7.sort()
console.log(arr7)

arr7.sort((a,b)=>{
    return b-a
})

console.log(arr7)