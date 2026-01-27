import {
  Button,
  Modal,
  ModalBody,
  ModalContent,
  ModalFooter,
  ModalHeader,
  useDisclosure,
} from '@heroui/react';
import React from 'react';

interface ConfirmModalProps {
  readonly title: string;
  readonly bodyText: string;
  readonly children: (onOpen: () => void) => React.ReactNode;
  readonly action: () => void;
}
function ConfirmModal({
  title,
  bodyText,
  children,
  action,
}: ConfirmModalProps) {
  const { isOpen, onOpen, onOpenChange } = useDisclosure();

  return (
    <>
      {children(onOpen)}
      <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">{title}</ModalHeader>
              <ModalBody>
                <p>{bodyText}</p>
              </ModalBody>
              <ModalFooter className="flex justify-between">
                <Button color="danger" onPress={onClose}>
                  Close
                </Button>
                <Button
                  color="primary"
                  onPress={() => {
                    action();
                    onClose();
                  }}
                >
                  Action
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </>
  );
}

export default ConfirmModal;
