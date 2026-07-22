select TotalRevenu.product_id, case when total_untis is not null then round(sum(revenu)/total_untis,2) else 0 end as average_price from
(
    select pre.product_id, case when purchase_date is not null then (price*units) else 0 end as revenu from Prices pre left join UnitsSold sld on sld.product_id = pre.product_id where (DATEDIFF(purchase_date,start_date) >= 0 and DATEDIFF(end_date,purchase_date) >= 0) or purchase_date is null
) TotalRevenu left join 
(
    select product_id,sum(units) as total_untis from UnitsSold group by product_id
) TotalSoldUnits on TotalRevenu.product_id=TotalSoldUnits.product_id group by TotalRevenu.product_id;