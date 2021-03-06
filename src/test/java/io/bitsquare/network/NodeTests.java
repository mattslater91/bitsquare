/*
 * This file is part of Bitsquare.
 *
 * Bitsquare is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bitsquare is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bitsquare. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bitsquare.network;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class NodeTests {

    @Test
    public void testEqualsAndHashCode() {
        Node node1a = Node.at("bitsquare1.example.com", "203.0.113.1");
        Node node1b = Node.at("bitsquare1.example.com", "203.0.113.1");

        assertThat(node1a, equalTo(node1a));

        assertThat(node1a, equalTo(node1b));
        assertThat(node1b, equalTo(node1a));

        assertThat(node1a, not((Object) equalTo(null)));
        assertThat(node1a, not((Object) equalTo("not a node")));

        assertThat(node1a, not(equalTo(Node.at("bitsquare2.example.com", node1a.getIp()))));
        assertThat(node1a, not(equalTo(Node.at(node1a.getName(), "203.0.113.2"))));
        assertThat(node1a, not(equalTo(Node.at(node1a.getName(), node1a.getIp(), Node.DEFAULT_PORT + 1))));

        Node node2 = Node.at("bitsquare2.example.com", "203.0.113.2");
        assertThat(node1a.hashCode(), equalTo(node1b.hashCode()));
        assertThat(node1a.hashCode(), not(equalTo(node2.hashCode())));

        assertThat(node1a.getPort(), equalTo(Node.DEFAULT_PORT));

        Node node3a = Node.at("bitsquare3.example.com", "203.0.113.3", 1234);
        Node node3b = Node.at("bitsquare3.example.com", "203.0.113.3", "1234");

        assertThat(node3a, equalTo(node3b));
    }

    @Test
    public void testToString() {
        Node node = Node.at("bitsquare1.example.com", "203.0.113.1", 5001);
        assertThat(node.toString(), equalTo("Node{name=bitsquare1.example.com, ip=203.0.113.1, port=5001}"));
    }
}