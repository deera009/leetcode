# Write your MySQL query statement below
select p.product_id , COALESCE(ROUND(sum(p.price * u.units)/sum(u.units),2),0) average_price
from prices p
left join unitssold u
on p.product_id = u.product_id
where (u.purchase_date >= p.start_date and 
u.purchase_date <= p.end_date)
OR u.purchase_date is null
group by p.product_id;
