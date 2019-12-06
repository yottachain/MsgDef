/*
    YottaChain Repairable Code API
	Copyright (c) 2019 YottaChain Foundation Ltd.  All rights reserved.

	Redistribution and use in source and binary forms, with or without
	modification, are permitted provided that the following conditions are met:

	* Redistributions of source code must retain the above copyright notice,
	  this list of conditions and the following disclaimer.
	* Redistributions in binary form must reproduce the above copyright notice,
	  this list of conditions and the following disclaimer in the documentation
	  and/or other materials provided with the distribution.
	* Neither the name of CM256 nor the names of its contributors may be
	  used to endorse or promote products derived from this software without
	  specific prior written permission.

	THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
	AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
	IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
	ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
	LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
	CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
	SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
	INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
	CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
	ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
	POSSIBILITY OF SUCH DAMAGE.
*/

#ifndef YTLRC_H
#define YTLRC_H

/*
 * Initialize
 * numGlobalRecoveryCount: number of global recovery chunks
 * maxHandles: maximum of decoders working at same time
 * return: 0 if fails
 */
short InitialLRC(short globalRecoveryCount, short maxHandles);

#define MAXRECOVERYCHUNKS   36
/*
 * Encode original data, return recovery chunks
 * originalChunks: chunks for original data, 1st byte of each chunk is its index
 * chunkSize: size of each chunk
 * originalCount: number of chunks of original data
 * pRecoveryData: required at least MAXRECOVERYCHUNKS*(chunkSize+1) space, return recovery chunks after encoding,
 *                length of each chunk is chunkSize+1, the leading byte of each chunk is index of this chunk
 * return: number of recovery chunks, <=0 fails
 */
short EncodeLRC(const void *originalShards[], unsigned short originalCount, unsigned long shardSize, void *pRecoveryData);

/*
 * Begin of new decode process
 * originalCount: number of chunks of original data
 * chunkSize: size of each chunk in byte including index byte
 * pData: require at least originalCount * (chunkSize-1) space, return original data if success
 * return: handle of this decode process, <0 fails (such as exceed maxHandles)
 */
short BeginDecode(unsigned short originalCount, unsigned long shardSize, void *pData);

/*
 * Decode one chunk for specific decode process
 * handle: handle of decode process
 * pChunk: data of this chunk
 * return: 0 if collected chunks are not enough for decoding,  >0 success, automatically free handle, <0 error
 */
short DecodeLRC(short handle, const void *pShard);

/*
 * Abandon a decode process
 */
void FreeHandle(short handle);

#endif  // YTLRC_H
