/*
 * Copyright 2017 The OpenDSP Project
 *
 * The OpenDSP Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package mobi.f2time.dorado.rest.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import mobi.f2time.dorado.rest.util.Constant;

/**
 * 
 * @author wangwp
 */
public final class DoradoServerBuilder {
	private int backlog = Constant.DEFAULT_BACKLOG;
	private int acceptors = Constant.DEFAULT_ACCEPTOR_COUNT;
	private int ioWorkers = Constant.DEFAULT_IO_WORKER_COUNT;

	private int minWorkers = Constant.DEFAULT_MIN_WORKER_THREAD;
	private int maxWorkers = Constant.DEFAULT_MAX_WORKER_THREAD;

	private int maxConnection = Integer.MAX_VALUE;
	private int maxPendingRequest = Constant.DEFAULT_MAX_PENDING_REQUEST;
	private int maxIdleTime = Constant.DEFAULT_MAX_IDLE_TIME;

	// 以下参数用于accept到服务器的socket
	private int sendBuffer = Constant.DEFAULT_SEND_BUFFER_SIZE;
	private int recvBuffer = Constant.DEFAULT_RECV_BUFFER_SIZE;

	private int maxPacketLength = Constant.DEFAULT_MAX_PACKET_LENGTH;
	private String[] scanPackages;
	private ExecutorService executor;

	private final int port;

	private DoradoServerBuilder(int port) {
		this.port = port;
	}

	public static DoradoServerBuilder forPort(int port) {
		return new DoradoServerBuilder(port);
	}

	public DoradoServerBuilder backlog(int backlog) {
		this.backlog = backlog;
		return this;
	}

	public DoradoServerBuilder acceptors(int acceptors) {
		this.acceptors = acceptors;
		return this;
	}

	public DoradoServerBuilder ioWorkers(int ioWorkers) {
		this.ioWorkers = ioWorkers;
		return this;
	}

	public DoradoServerBuilder minWorkers(int minWorkers) {
		this.minWorkers = minWorkers;
		return this;
	}

	public DoradoServerBuilder maxWorkers(int maxWorkers) {
		this.maxWorkers = maxWorkers;
		return this;
	}

	public DoradoServerBuilder maxIdleTime(int maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
		return this;
	}

	public DoradoServerBuilder sendBuffer(int sendBuffer) {
		this.sendBuffer = sendBuffer;
		return this;
	}

	public DoradoServerBuilder recvBuffer(int recvBuffer) {
		this.recvBuffer = recvBuffer;
		return this;
	}

	public DoradoServerBuilder maxConnection(int maxConnection) {
		this.maxConnection = maxConnection;
		return this;
	}

	public DoradoServerBuilder maxPendingRequest(int maxPendingRequest) {
		this.maxPendingRequest = maxPendingRequest;
		return this;
	}

	public DoradoServerBuilder maxPacketLength(int maxPacketLength) {
		this.maxPacketLength = maxPacketLength;
		return this;
	}

	public DoradoServerBuilder scanPackages(String... packages) {
		this.scanPackages = packages;
		return this;
	}

	public int getBacklog() {
		return backlog;
	}

	public int getAcceptors() {
		return acceptors;
	}

	public int getIoWorkers() {
		return ioWorkers;
	}

	public int getMinWorkers() {
		return minWorkers;
	}

	public int getMaxWorkers() {
		return maxWorkers;
	}

	public int getMaxConnection() {
		return maxConnection;
	}

	public int getMaxPendingRequest() {
		return maxPendingRequest;
	}

	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	public int getSendBuffer() {
		return sendBuffer;
	}

	public int getRecvBuffer() {
		return recvBuffer;
	}

	public int getMaxPacketLength() {
		return maxPacketLength;
	}

	public String[] scanPackages() {
		return this.scanPackages;
	}

	public ExecutorService executor() {
		return this.executor;
	}

	public int getPort() {
		return port;
	}

	public DoradoServer build() {
		if (scanPackages == null || scanPackages.length == 0) {
			throw new IllegalArgumentException("scanPackage should not be null");
		}

		if (minWorkers > 0 && maxWorkers > 0 && (maxWorkers >= minWorkers) && maxPendingRequest > 0) {
			executor = new ThreadPoolExecutor(minWorkers, maxWorkers, 5, TimeUnit.MINUTES,
					new LinkedBlockingQueue<>(maxPendingRequest), new ThreadPoolExecutor.DiscardPolicy());
		}
		return new DoradoServer(this);
	}
}
