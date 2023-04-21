import { Router } from 'express';

import {
	getAll_HandyPicking_Detail,
	createNew_Handy_Picking_Detail,
} from '../controller/handyPickingDetail.controller.js';

const router = Router();

// GET
router.get('/handyPickingDetail', getAll_HandyPicking_Detail);

router.post('/handyPickingDetail', createNew_Handy_Picking_Detail);

export default router;
