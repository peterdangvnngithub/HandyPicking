import { getConnection } from '../database/connection.js';
import sql from 'mssql';

export const checkApiStatus = async (req, res) => {
	try {
		res.status(200).end();
	} catch (error) {
		console.log("500");
		res.status(500).send(error.message);
	}
};

export const check_Exists_HandyPicking_Detail = async (req, res) => {
	try {
		const pool = await getConnection();
		const result = await pool
		.request()
		.input("series", req.params.series)
		.query('SELECT * FROM HANDY_PICKING_DETAIL WHERE SERIES = @series');
		console.log(result.recordset);	
		res.json(result.recordset);
	} catch (error) {
		console.error(error);
		res.status(500).send("Internal Server Error");
	}
};

export const getAll_HandyPicking_Detail = async (req, res) => {
	try {
		const pool = await getConnection();
		const result = await pool.request().query('SELECT * FROM HANDY_PICKING_DETAIL');
		console.log(result);
		res.status(200).json(result.recordset);
	} catch (error) {
		res.status(500).send(error.message);
	}
};

export const createNew_Handy_Picking_Detail = async (req, res) => {
	var result = req.body;

	try {
		const pool = await getConnection();	

		console.log(result[0]);

		pool.connect().then(() => {
			const table = new sql.Table('HANDY_PICKING_DETAIL');
			table.columns.add('PICKING_LIST_NO',	sql.NVarChar(50),	{ nullable: false, primary: true });
			table.columns.add('INVOICE_NO',			sql.NVarChar(50),	{ nullable: true });
			table.columns.add('SALE_ORDER',			sql.VarChar(30),	{ nullable: false });
			table.columns.add('ITEM_CODE',			sql.VarChar(30),	{ nullable: false });
			table.columns.add('LOT_ID',				sql.VarChar(50),	{ nullable: false });
			table.columns.add('QUANTITY',			sql.Int,			{ nullable: false });
			table.columns.add('PALLET_NO',			sql.VarChar(50),	{ nullable: false });
			table.columns.add('SERIES',				sql.VarChar(50),	{ nullable: false, primary: true });
			table.columns.add('CUS_ITEM_CODE',		sql.NVarChar(50),	{ nullable: false, primary: true });
			table.columns.add('TVC_ITEM_CODE',		sql.NVarChar(50),	{ nullable: true });
			table.columns.add('CUSTOMER_PO',		sql.NVarChar(50),	{ nullable: true });
			table.columns.add('QTY_CARTON',			sql.Int,			{ nullable: false });
			table.columns.add('QTY_PER_CARTON',		sql.Int,			{ nullable: false });
			table.columns.add('QTY_TOTAL',			sql.Int,			{ nullable: false });
			table.columns.add('NET_WEIGHT',			sql.Decimal,		{ nullable: false });
			table.columns.add('NET_WEIGHT_TOTAL',	sql.Decimal,		{ nullable: false });
			table.columns.add('GROSS_WEIGHT',		sql.Decimal,		{ nullable: false });
			table.columns.add('LOT_NO',				sql.NVarChar(150),	{ nullable: true });
			table.columns.add('CREATE_DATE',		sql.DateTime,		{ nullable: false });
			table.columns.add('CREATE_BY',			sql.VarChar,		{ nullable: false });
			table.columns.add('EDIT_DATE',			sql.DateTime,		{ nullable: true });
			table.columns.add('EDIT_BY',			sql.NVarChar(50),	{ nullable: true });
			table.columns.add('STATUS',				sql.Int,			{ nullable: true });
			table.columns.add('COLUMN1',			sql.VarChar(30),	{ nullable: true });
			table.columns.add('COLUMN2',			sql.VarChar(30),	{ nullable: true });
			table.columns.add('COLUMN3',			sql.VarChar(30),	{ nullable: true });
			table.columns.add('COLUMN4',			sql.VarChar(30),	{ nullable: true });
			table.columns.add('COLUMN5',			sql.VarChar(30),	{ nullable: true });

			for (let i = 0; i <= result.length - 1; i++) {
				table.rows.add(
					result[i]['PICKING_LIST_NO'],
					result[i]['INVOICE_NO'],
					result[i]['SALE_ORDER'],
					result[i]['ITEM_CODE'],
					result[i]['LOT_ID'],
					result[i]['QUANTITY'],
					result[i]['PALLET_NO'],
					result[i]['SERIES'],
					result[i]['CUS_ITEM_CODE'],
					result[i]['TVC_ITEM_CODE'],
					result[i]['CUSTOMER_PO'],
					result[i]['QTY_CARTON'],
					result[i]['QTY_PER_CARTON'], 
					result[i]['QTY_TOTAL'],
					result[i]['NET_WEIGHT'],
					result[i]['NET_WEIGHT_TOTAL'],
					result[i]['GROSS_WEIGHT'],
					result[i]['LOT_NO'],
					new Date(),
					result[i]['CREATE_BY'],
					result[i]['EDIT_DATE'],
					result[i]['EDIT_BY'],
					result[i]['STATUS'],
					result[i]['COLUMN1'],
					result[i]['COLUMN2'],
					result[i]['COLUMN3'],
					result[i]['COLUMN4'],
					result[i]['COLUMN5']
				);
			}

			const request = new sql.Request();	
			const result_2 = request.bulk(table, (err, result) => {
				// Error checks
				if (err) {
					console.log('Post bulk error: ' + err);
					res.status(500).send({ message: 'Bulk insert operation failed.' });
				} else {
					console.log('Post bulk success!');
					res.status(200).send({ message: 'Bulk insert operation successful.' });
				}
			});
		});
	} catch (error) {
		console.error(error.message);
		res.status(500);
		res.send(error.message);
	}
};

export const checkListHandyDetailsExistence = async (req, res) => {
	const list_handyDetail = req.body;

	try {
		console.log(list_handyDetail[0]);
		const request	= await getConnection();
		const promises	= list_handyDetail.map((HANDY_PICKING_DETAIL) => {
		const query		= `SELECT COUNT(*) AS count FROM HANDY_PICKING_DETAIL WHERE SERIES = '${HANDY_PICKING_DETAIL.SERIES}'`;
		return request.query(query);
    });

    const results			= await Promise.all(promises);
    const existenceCounts	= results.map((result) => result.recordset[0].count);
    console.log(existenceCounts);
    res.status(200).json(existenceCounts[0]);
	} catch (err) {
		console.error('Error while checking handy details existence:', err);
		res.status(500).json({ error: 'An error occurred while checking handy details existence.' });
	}
};