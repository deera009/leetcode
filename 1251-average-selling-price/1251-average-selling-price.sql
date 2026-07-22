# Write your MySQL query statement below
select t1.product_id,IFNULL(ROUND(SUM(t1.price * t2.units) / SUM(t2.units), 2), 0) AS average_price from 
(select product_id, start_date,end_date,price from prices) t1
left join
(select product_id,purchase_date,units from unitssold) t2
on t1.product_id=t2.product_id and t2.purchase_date between t1.start_date and end_date
group by t1.product_id