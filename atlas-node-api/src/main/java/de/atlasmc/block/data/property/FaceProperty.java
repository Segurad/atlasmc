package de.atlasmc.block.data.property;

import de.atlasmc.block.data.BlockData;
import de.atlasmc.block.data.FaceAttachable;
import de.atlasmc.block.data.FaceAttachable.AttachedFace;

class FaceProperty extends AbstractEnumProperty<AttachedFace> {

	public FaceProperty() {
		super("face", AttachedFace.class);
	}

	@Override
	public void set(BlockData data, AttachedFace value) {
		if (data instanceof FaceAttachable face)
			face.setAttachedFace(value);
	}

	@Override
	public AttachedFace get(BlockData data) {
		if (data instanceof FaceAttachable face)
			return face.getAttachedFace();
		return null;
	}

	@Override
	public boolean supports(BlockData data) {
		return data instanceof FaceAttachable;
	}

}
