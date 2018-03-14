package com.specialtroops.chapter03.tools;

import java.util.List;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class JPSTest {

	public static void main(String []args) {// jps-test测试使用的
		List<VirtualMachineDescriptor> machineDescriptors = VirtualMachine.list();
		for(VirtualMachineDescriptor machineDescriptor : machineDescriptors) {
			System.out.println(machineDescriptor.id() + "\t" + machineDescriptor.displayName());
		}
	}
}
