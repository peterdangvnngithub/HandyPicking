import { Router } from 'express';
import { getAll_HandyPicking_MS, createNew_Handy_Picking_MS } from '../controller/handyPickingMS.controller.js';

const router = Router();

// GET
router.get('/handyPickingMS', getAll_HandyPicking_MS);

// POST
router.post('/handyPickingMS', createNew_Handy_Picking_MS);

// DELETE
router.delete('/handyPickingMS', getAll_HandyPicking_MS);

// PUT
router.put('/handyPickingMS', getAll_HandyPicking_MS);

// GET
router.get('/handyPickingMS', getAll_HandyPicking_MS);

export default router;
