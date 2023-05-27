SELECT * FROM dbo.Account

SELECT * FROM dbo.Products

SELECT COUNT(*)
FROM dbo.Products

SELECT * FROM dbo.Products
ORDER BY product_id
OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY

SELECT COUNT(*) 
FROM Products
WHERE product_name LIKE '%512%'

INSERT INTO dbo.Account
VALUES ('fgdf','dgfg','111','sdfsdf','dsff','sfdf')

SELECT MAX(order_id) FROM dbo.Orders

SELECT * FROM dbo.Orders

SELECT* FROM dbo.Orders_detail

SELECT order_id FROM dbo.Orders
WHERE user_mail='duydt@fpt.com.vn'

SELECT* FROM dbo.Orders_detail
WHERE order_id=8

SELECT * FROM dbo.Account

SELECT * FROM dbo.Orders

SELECT* FROM dbo.Orders_detail