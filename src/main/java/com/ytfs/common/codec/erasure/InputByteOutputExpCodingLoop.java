package com.ytfs.common.codec.erasure;

public class InputByteOutputExpCodingLoop extends CodingLoopBase {

    @Override
    public void codeSomeShards(
            byte[][] matrixRows,
            byte[][] inputs, int inputCount,
            byte[][] outputs, int outputCount,
            int offset, int byteCount) {
        {
            final int iInput = 0;
            final byte[] inputShard = inputs[iInput];
            for (int iByte = offset; iByte < offset + byteCount; iByte++) {
                final byte inputByte = inputShard[iByte];
                for (int iOutput = 0; iOutput < outputCount; iOutput++) {
                    final byte[] outputShard = outputs[iOutput];
                    final byte[] matrixRow = matrixRows[iOutput];
                    outputShard[iByte] = Galois.multiply(matrixRow[iInput], inputByte);
                }
            }
        }
        for (int iInput = 1; iInput < inputCount; iInput++) {
            final byte[] inputShard = inputs[iInput];
            for (int iByte = offset; iByte < offset + byteCount; iByte++) {
                final byte inputByte = inputShard[iByte];
                for (int iOutput = 0; iOutput < outputCount; iOutput++) {
                    final byte[] outputShard = outputs[iOutput];
                    final byte[] matrixRow = matrixRows[iOutput];
                    outputShard[iByte] ^= Galois.multiply(matrixRow[iInput], inputByte);
                }
            }
        }
    }

}
